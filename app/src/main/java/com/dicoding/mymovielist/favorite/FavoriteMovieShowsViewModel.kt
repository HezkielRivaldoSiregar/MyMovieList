package com.dicoding.mymovielist.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

class FavoriteMovieShowsViewModel (private val movieShowsRepository: MovieShowsRepository) : ViewModel() {

    fun getFavoriteMovies(): LiveData<PagedList<Movies>> =
        movieShowsRepository.getFavoriteMovies()

    fun setFavoriteMovie(movie: Movies) {
        val newState = !movie.favorited
        movieShowsRepository.setFavoriteMovie(movie, newState)
    }

    fun getFavoriteShow(): LiveData<PagedList<TvShows>> =
        movieShowsRepository.getFavoriteTvShows()

    fun setFavoriteShow(show: TvShows) {
        val newState = !show.favorited
        movieShowsRepository.setFavoriteTvShow(show, newState)
    }

}