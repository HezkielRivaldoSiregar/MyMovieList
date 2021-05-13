package com.dicoding.mymovielist.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.vo.Resource


class MoviesShowsViewModel (private val movieShowsRepository: MovieShowsRepository) : ViewModel() {

    fun getMoviesData(): LiveData<Resource<PagedList<Movies>>> =
        movieShowsRepository.getAllMovies()

    fun getTvShowsData(): LiveData<Resource<PagedList<TvShows>>> =
        movieShowsRepository.getAllTvShows()

}
