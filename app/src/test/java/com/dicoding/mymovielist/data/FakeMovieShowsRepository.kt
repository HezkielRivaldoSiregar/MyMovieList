package com.dicoding.mymovielist.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.data.remote.RemoteDataSource
import com.dicoding.mymovielist.data.remote.response.MovieResponse
import com.dicoding.mymovielist.data.remote.response.TvResponse

class FakeMovieShowsRepository (private val remoteDataSource: RemoteDataSource) : MovieShowsDataSource {
    override fun getAllMovies(): LiveData<List<Movies>>{
        val result = MutableLiveData<List<Movies>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback{
            override fun onAllMoviesReceived(movieResponses: List<MovieResponse>){
                val listMovie = ArrayList<Movies>()
                for(response in movieResponses){
                    val movie = Movies(
                        response.image,
                        response.title,
                        response.overview,
                        response.director,
                        response.rating,
                        response.releaseDate,
                        response.genre,
                        response.status,
                        response.duration,
                    )
                    listMovie.add(movie)
                }
                result.postValue(listMovie)
            }
        })
        return result
    }

    override fun getAllShows(): LiveData<List<TvShows>> {
        val result = MutableLiveData<List<TvShows>>()
        remoteDataSource.getAllShows(object : RemoteDataSource.LoadTvShowsCallback{
            override fun onAllTvShowsReceived(tvShowResponses: List<TvResponse>) {
                val listShow = ArrayList<TvShows>()
                for(response in tvShowResponses){
                    val shows = TvShows(
                        response.image,
                        response.title,
                        response.overview,
                        response.creator,
                        response.rating,
                        response.releaseDate,
                        response.seasons,
                        response.genre,
                        response.status,
                        response.duration
                    )
                    listShow.add(shows)
                }
                result.postValue(listShow)
            }
        })
        return result
    }
}