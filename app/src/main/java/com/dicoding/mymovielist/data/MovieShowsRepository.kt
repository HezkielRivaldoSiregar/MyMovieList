package com.dicoding.mymovielist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.data.remote.RemoteDataSource
import com.dicoding.mymovielist.data.remote.response.MovieResponse
import com.dicoding.mymovielist.data.remote.response.TvResponse

class MovieShowsRepository private constructor(private val remoteDataSource: RemoteDataSource) : MovieShowsDataSource {

    companion object {
        @Volatile
        private var instance: MovieShowsRepository? = null
        fun getInstance(remoteData: RemoteDataSource): MovieShowsRepository = instance ?: synchronized(this) {
                instance ?: MovieShowsRepository(remoteData)
            }
    }

    override fun getAllMovies(): LiveData<List<Movies>> {
        val movieResults = MutableLiveData<List<Movies>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<Movies>()
                for (response in movieResponses) {
                    val movie = Movies(
                        response.image,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.genre,
                        response.backdrop,
                        response.trailer
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })

        return movieResults
    }

    override fun getAllShows(): LiveData<List<TvShows>> {
        val tvShowResults = MutableLiveData<List<TvShows>>()
        remoteDataSource.getAllShows(object : RemoteDataSource.LoadTvShowsCallback {
            override fun onAllTvShowsReceived(tvShowResponses: List<TvResponse>) {
                val tvShowList = ArrayList<TvShows>()
                for (response in tvShowResponses) {
                    val tvshow = TvShows(
                        response.image,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.seasons,
                        response.genre,
                        response.backdrop,
                        response.trailer
                    )
                    tvShowList.add(tvshow)
                }
                tvShowResults.postValue(tvShowList)
            }
        })

        return tvShowResults
    }

}