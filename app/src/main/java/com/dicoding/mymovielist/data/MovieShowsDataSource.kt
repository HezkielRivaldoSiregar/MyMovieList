package com.dicoding.mymovielist.data

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.vo.Resource

interface MovieShowsDataSource {
        fun getAllMovies(): LiveData<Resource<PagedList<Movies>>>

        fun getMoviesById(movieId: Int): LiveData<Resource<Movies>>

        fun getAllTvShows(): LiveData<Resource<PagedList<TvShows>>>

        fun getTvShowsById(tvShowId: Int): LiveData<Resource<TvShows>>

        fun setFavoriteMovie(movie: Movies, state: Boolean)

        fun setFavoriteTvShow(tvShow: TvShows, state: Boolean)

        fun getFavoriteMovies(): LiveData<PagedList<Movies>>

        fun getFavoriteTvShows(): LiveData<PagedList<TvShows>>
}