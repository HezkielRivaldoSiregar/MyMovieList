package com.dicoding.mymovielist.data.remote

import android.os.Handler
import android.os.Looper
import com.dicoding.mymovielist.data.EspressoIdlingResources
import com.dicoding.mymovielist.data.JsonHelper
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

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

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResources.increment()
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                callback.onAllMoviesReceived(jsonHelper.loadMovies())
                EspressoIdlingResources.decrement()
            }, SERVICE_LATENCY_IN_MILLIS)
        }
    }
    fun getAllShows(callback: LoadTvShowsCallback) {
        EspressoIdlingResources.increment()
        Looper.myLooper()?.let {
            Handler(it).postDelayed({
                    callback.onAllTvShowsReceived(jsonHelper.loadTvshows())
                    EspressoIdlingResources.decrement()
                }, SERVICE_LATENCY_IN_MILLIS)
            }
    }

    interface LoadTvShowsCallback {
        fun onAllTvShowsReceived(tvShowResponses: List<TvShows>)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieRespons: List<Movies>)
    }
}