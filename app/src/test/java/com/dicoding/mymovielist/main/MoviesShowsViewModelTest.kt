package com.dicoding.mymovielist.main

import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class MoviesShowsViewModelTest {

    private lateinit var viewModel: MoviesShowsViewModel

    @Before
    fun setUp(){
        viewModel = MoviesShowsViewModel()
    }

    @Test
    fun getMoviesData() {
        val moviesEntities = viewModel.getMoviesData()
        assertNotNull(moviesEntities)
        assertEquals(10, moviesEntities.size)
    }

    @Test
    fun getTvShowsData() {
        val showsEntities = viewModel.getTvShowsData()
        assertNotNull(showsEntities)
        assertEquals(10, showsEntities.size)
    }
}