package com.dicoding.mymovielist.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

class MoviesShowsDetailViewModel(private val movieShowsRepository: MovieShowsRepository): ViewModel() {
    private lateinit var title: String

    fun getMovie() : LiveData<List<Movies>> = movieShowsRepository.getAllMovies()
    fun getTvshow() : LiveData<List<TvShows>> = movieShowsRepository.getAllShows()

    fun setSelectedMovies(movieTitle: String){
        this.title = movieTitle
    }
    fun setSelectedShows(showTitle: String){
        this.title = showTitle
    }

}