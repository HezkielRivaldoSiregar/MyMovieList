package com.dicoding.mymovielist.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

class LocalDataSource private constructor(private val mMovieShowDao: MovieShowDao) {
    companion object {
        private var INSTANCE: LocalDataSource? = null
        fun getInstance(movieShowDao: MovieShowDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieShowDao)
    }

    fun getMovies(): DataSource.Factory<Int, Movies> = mMovieShowDao.getMovies()

    fun getShow(): DataSource.Factory<Int, TvShows> = mMovieShowDao.getShow()

    fun getMovieById(id : Int): LiveData<Movies> = mMovieShowDao.getMovieById(id)

    fun getShowById(id : Int): LiveData<TvShows> = mMovieShowDao.getShowById(id)

    fun insertMovie(movies : List<Movies>) = mMovieShowDao.insertMovie(movies)

    fun insertShow(shows : List<TvShows>) = mMovieShowDao.insertShow(shows)

    fun getFavoriteMovie(): DataSource.Factory<Int, Movies> = mMovieShowDao.getFavoriteMovie()

    fun getFavoriteShow(): DataSource.Factory<Int, TvShows> = mMovieShowDao.getFavoriteShow()

    fun setFavoriteMovie(movie: Movies, newState: Boolean) {
        movie.favorited= newState
        mMovieShowDao.updateMovie(movie)
    }

    fun setFavoriteTvShow(show: TvShows, newState: Boolean) {
        show.favorited = newState
        mMovieShowDao.updateShow(show)
    }

}