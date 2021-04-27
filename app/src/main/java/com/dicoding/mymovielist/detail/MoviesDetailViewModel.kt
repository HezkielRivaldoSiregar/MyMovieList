package com.dicoding.mymovielist.detail

import androidx.lifecycle.ViewModel
import com.dicoding.mymovielist.data.Movies
import com.dicoding.mymovielist.data.MoviesTvShowsData

class MoviesDetailViewModel: ViewModel() {

    private lateinit var title: String

    fun setSelectedMovies(title: String){
        this.title = title
    }

    fun getMovies(): Movies {
        lateinit var movies: Movies
        val moviesEntities = MoviesTvShowsData.generateMoviesData()
        for(moviesEntity in moviesEntities){
            if(moviesEntity.title == title){
                movies = moviesEntity
            }
        }
        return movies
    }

}