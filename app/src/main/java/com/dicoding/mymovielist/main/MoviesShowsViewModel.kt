package com.dicoding.mymovielist.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows


class MoviesShowsViewModel (private val movieShowsRepository: MovieShowsRepository) : ViewModel() {

    fun getMoviesData(): LiveData<List<Movies>> = movieShowsRepository.getAllMovies()
    fun getTvShowsData(): LiveData<List<TvShows>> = movieShowsRepository.getAllShows()

}
