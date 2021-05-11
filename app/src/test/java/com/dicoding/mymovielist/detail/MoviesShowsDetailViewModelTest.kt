package com.dicoding.mymovielist.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.MoviesTvShowsData
import com.dicoding.mymovielist.data.local.TvShows
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesShowsDetailViewModelTest {

    private lateinit var viewModel: MoviesShowsDetailViewModel
    private val showsData = MoviesTvShowsData.generateTvShowsData()[0]
    private val moviesData = MoviesTvShowsData.generateMoviesData()[0]
    private val movieTitle = moviesData.title
    private val showTitle = showsData.title

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieShowsRepository: MovieShowsRepository

    @Mock
    private lateinit var movieObserver: Observer<List<Movies>>

    @Mock
    private lateinit var showObserver: Observer<List<TvShows>>

    @Before
    fun setUp() {
        viewModel = MoviesShowsDetailViewModel(movieShowsRepository)
        viewModel.setSelectedMovies(movieTitle)
        viewModel.setSelectedShows(showTitle)
    }

    @Test
    fun getMovie() {
        val movie = MutableLiveData<List<Movies>>()
        movie.value = listOf(moviesData)

        Mockito.`when`(movieShowsRepository.getAllMovies()).thenReturn(movie)
        val movieEntity = viewModel.getMovie().value
        Mockito.verify(movieShowsRepository).getAllMovies()

        assertNotNull(movieEntity)
        assertEquals(moviesData.genre, movieEntity?.get(0)?.genre)
        assertEquals(moviesData.image, movieEntity?.get(0)?.image)
        assertEquals(moviesData.overview, movieEntity?.get(0)?.overview)
        assertEquals(moviesData.releaseDate, movieEntity?.get(0)?.releaseDate)
        assertEquals(moviesData.title, movieEntity?.get(0)?.title)
        assertEquals(moviesData.backdrop, movieEntity?.get(0)?.backdrop)
        assertEquals(moviesData.trailer, movieEntity?.get(0)?.trailer)



        viewModel.getMovie().observeForever(movieObserver)
        Mockito.verify(movieObserver).onChanged(listOf(moviesData))
    }

    @Test
    fun getTvShow() {
        val tvShow = MutableLiveData<List<TvShows>>()
        tvShow.value = listOf(showsData)

        Mockito.`when`(movieShowsRepository.getAllShows()).thenReturn(tvShow)
        val showEntity = viewModel.getTvshow().value
        Mockito.verify(movieShowsRepository).getAllShows()

        assertNotNull(showEntity)
        assertEquals(showsData.genre, showEntity?.get(0)?.genre)
        assertEquals(showsData.image, showEntity?.get(0)?.image)
        assertEquals(showsData.overview, showEntity?.get(0)?.overview)
        assertEquals(showsData.releaseDate, showEntity?.get(0)?.releaseDate)
        assertEquals(showsData.title, showEntity?.get(0)?.title)
        assertEquals(showsData.seasons, showEntity?.get(0)?.seasons)
        assertEquals(moviesData.backdrop, showEntity?.get(0)?.backdrop)
        assertEquals(moviesData.trailer, showEntity?.get(0)?.trailer)

        viewModel.getTvshow().observeForever(showObserver)
        Mockito.verify(showObserver).onChanged(listOf(showsData))
    }



}