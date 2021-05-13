package com.dicoding.mymovielist.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.vo.Resource

class MoviesShowsDetailViewModel(private val movieShowsRepository: MovieShowsRepository): ViewModel() {
    private val idMovie = MutableLiveData<Int>()
    private val idShow = MutableLiveData<Int>()

    fun setSelectedMovies(idMovie: Int) {
        this.idMovie.value = idMovie
    }

    fun setSelectedShows(idShow: Int) {
        this.idShow.value = idShow
    }

    var movie: LiveData<Resource<Movies>> = Transformations.switchMap(idMovie) {
        movieShowsRepository.getMoviesById(it)
    }

    var tvShow: LiveData<Resource<TvShows>> = Transformations.switchMap(idShow) {
            movieShowsRepository.getTvShowsById(it)
        }

    fun setFavorite() {

        val resourceMovie = movie.value
        val resourceShow = tvShow.value

        if (resourceMovie != null) {
            val movieWithData = resourceMovie.data

            if (movieWithData != null) {
                val newState = !movieWithData.favorited
                movieShowsRepository.setFavoriteMovie(movieWithData, newState)
            }
        }

        if (resourceShow != null) {
            val tvShowWithData = resourceShow.data

            if (tvShowWithData != null) {
                val newState = !tvShowWithData.favorited
                movieShowsRepository.setFavoriteTvShow(tvShowWithData, newState)
            }
        }
    }

}