package com.dicoding.mymovielist.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.MoviesTvShowsData
import com.dicoding.mymovielist.data.local.TvShows
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesShowsViewModelTest {

    private lateinit var viewModel: MoviesShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieShowsRepository: MovieShowsRepository

    @Mock
    private lateinit var movieObserver: Observer<List<Movies>>

    @Mock
    private lateinit var showsObserver: Observer<List<TvShows>>

    @Before
    fun setUp(){
        viewModel = MoviesShowsViewModel(movieShowsRepository)
    }

    @Test
    fun getMoviesData() {
        val moviesData = MoviesTvShowsData.generateMoviesData()
        val movie = MutableLiveData<List<Movies>>()
        movie.value = moviesData

        Mockito.`when`(movieShowsRepository.getAllMovies()).thenReturn(movie)
        val movieEntities = viewModel.getMoviesData().value
        Mockito.verify(movieShowsRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMoviesData().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(moviesData)
    }

    @Test
    fun getTvShowsData() {
        val showsData = MoviesTvShowsData.generateTvShowsData()
        val show = MutableLiveData<List<TvShows>>()
        show.value = showsData

        Mockito.`when`(movieShowsRepository.getAllShows()).thenReturn(show)
        val showEntities = viewModel.getTvShowsData().value
        Mockito.verify(movieShowsRepository).getAllShows()
        assertNotNull(showEntities)
        assertEquals(10, showEntities?.size)

        viewModel.getTvShowsData().observeForever(showsObserver)
        Mockito.verify(showsObserver).onChanged(showsData)
    }
}