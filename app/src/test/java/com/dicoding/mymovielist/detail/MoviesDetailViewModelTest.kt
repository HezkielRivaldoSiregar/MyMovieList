package com.dicoding.mymovielist.detail

import com.dicoding.mymovielist.data.MoviesTvShowsData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesDetailViewModelTest {

    private lateinit var viewModel: MoviesDetailViewModel
    private val moviesData = MoviesTvShowsData.generateMoviesData()[0]
    private val title = moviesData.title

    @Before
    fun setUp(){
        viewModel = MoviesDetailViewModel()
        viewModel.setSelectedMovies(title)
    }

    @Test
    fun getMovies() {
        viewModel.setSelectedMovies(moviesData.title)
        val moviesEntity = viewModel.getMovies()
        assertNotNull(moviesEntity)
        assertEquals(moviesData.image, moviesEntity.image)
        assertEquals(moviesData.title, moviesEntity.title)
        assertEquals(moviesData.overview, moviesEntity.overview)
        assertEquals(moviesData.director, moviesEntity.director)
        assertEquals(moviesData.rating, moviesEntity.rating)
        assertEquals(moviesData.releaseDate, moviesEntity.releaseDate)
        assertEquals(moviesData.genre, moviesEntity.genre)
        assertEquals(moviesData.status, moviesEntity.status)
        assertEquals(moviesData.duration, moviesEntity.duration)
    }
}