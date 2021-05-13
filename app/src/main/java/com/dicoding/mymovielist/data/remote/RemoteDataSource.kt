package com.dicoding.mymovielist.data.remote

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovielist.utils.EspressoIdlingResources
import com.dicoding.mymovielist.utils.JsonHelper
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.data.remote.response.ApiResponse

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(): LiveData<ApiResponse<List<Movies>>> {
        EspressoIdlingResources.increment()
        val resultMovies = MutableLiveData<ApiResponse<List<Movies>>>()
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                resultMovies.value = ApiResponse.success(jsonHelper.loadMovies())
                EspressoIdlingResources.decrement()
            }, SERVICE_LATENCY_IN_MILLIS)
        }
        return resultMovies
    }

    fun getAllShows() : LiveData<ApiResponse<List<TvShows>>> {
        EspressoIdlingResources.increment()
        val resultShows = MutableLiveData<ApiResponse<List<TvShows>>>()
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                    resultShows.value = ApiResponse.success(jsonHelper.loadTvshows())
                    EspressoIdlingResources.decrement()
                }, SERVICE_LATENCY_IN_MILLIS)
            }
        return resultShows
    }
}