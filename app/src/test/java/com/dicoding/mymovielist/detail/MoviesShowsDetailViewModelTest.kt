package com.dicoding.mymovielist.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.utils.MoviesTvShowsData
import com.dicoding.mymovielist.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import org.junit.Test
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesShowsDetailViewModelTest {

    private lateinit var viewModel: MoviesShowsDetailViewModel
    private val showsData = MoviesTvShowsData.generateTvShowsData()[0]
    private val moviesData = MoviesTvShowsData.generateMoviesData()[0]
    private val idMovie = moviesData.id
    private val idShow = showsData.id

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieShowsRepository: MovieShowsRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<Movies>>

    @Mock
    private lateinit var showObserver: Observer<Resource<TvShows>>

    @Before
    fun setUp() {
        viewModel = MoviesShowsDetailViewModel(movieShowsRepository)
        viewModel.setSelectedMovies(idMovie)
        viewModel.setSelectedShows(idShow)
    }

    @Test
    fun getMovie() {
        val movieDetail = Resource.success(MoviesTvShowsData.generateMoviesData()[0])
        val movie = MutableLiveData<Resource<Movies>>()
        movie.value = movieDetail

        `when`(movieShowsRepository.getMoviesById(idMovie)).thenReturn(movie)
        viewModel.movie.observeForever(movieObserver)
        verify(movieObserver).onChanged(movieDetail)
    }

    @Test
    fun getTvShows() {
        val showDetail = Resource.success(MoviesTvShowsData.generateTvShowsData()[0])
        val tvShow = MutableLiveData<Resource<TvShows>>()
        tvShow.value = showDetail

        `when`(movieShowsRepository.getTvShowsById(idShow)).thenReturn(tvShow)
        viewModel.tvShow.observeForever(showObserver)
        verify(showObserver).onChanged(showDetail)
    }

    @Test
    fun setFavoriteMovie() {
        val favoriteMovie = Resource.success(MoviesTvShowsData.generateMoviesData()[0])
        val movie = MutableLiveData<Resource<Movies>>()
        val newState = !moviesData.favorited
        movie.value = favoriteMovie
        `when`(movieShowsRepository.getMoviesById(idMovie)).thenReturn(movie)

        doNothing().`when`(movieShowsRepository).setFavoriteMovie(moviesData, newState)
        viewModel.movie.observeForever(movieObserver)
        viewModel.setFavorite()
        verify(movieShowsRepository, Mockito.times(1)).setFavoriteMovie(moviesData, newState)
    }

    @Test
    fun setFavoriteTvShow() {
        val favoriteShow = Resource.success(MoviesTvShowsData.generateTvShowsData()[0])
        val tvShow = MutableLiveData<Resource<TvShows>>()
        val newState = !showsData.favorited
        tvShow.value = favoriteShow
        `when`(movieShowsRepository.getTvShowsById(idShow)).thenReturn(tvShow)

        doNothing().`when`(movieShowsRepository).setFavoriteTvShow(showsData, newState)
        viewModel.tvShow.observeForever(showObserver)
        viewModel.setFavorite()
        verify(movieShowsRepository, Mockito.times(1)).setFavoriteTvShow(showsData, newState)
    }

}