package com.dicoding.mymovielist.main

import androidx.lifecycle.ViewModel
import com.dicoding.mymovielist.data.Movies
import com.dicoding.mymovielist.data.MoviesTvShowsData
import com.dicoding.mymovielist.data.TvShows


class MoviesShowsViewModel: ViewModel() {

    fun getMoviesData(): List<Movies> = MoviesTvShowsData.generateMoviesData()

    fun getTvShowsData(): List<TvShows> = MoviesTvShowsData.generateTvShowsData()
}