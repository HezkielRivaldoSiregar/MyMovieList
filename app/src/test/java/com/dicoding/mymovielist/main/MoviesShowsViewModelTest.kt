package com.dicoding.mymovielist.main

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.vo.Resource
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MoviesShowsViewModelTest {

    private lateinit var viewModel: MoviesShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieShowsRepository: MovieShowsRepository

    @Mock
    private lateinit var movieObserver: Observer<Resource<PagedList<Movies>>>

    @Mock
    private lateinit var showsObserver: Observer<Resource<PagedList<TvShows>>>

    @Mock
    private lateinit var moviePagedList: PagedList<Movies>

    @Mock
    private lateinit var showPagedList: PagedList<TvShows>

    @Before
    fun setUp(){
        viewModel = MoviesShowsViewModel(movieShowsRepository)
    }

    @Test
    fun getMoviesData() {
        val moviesData = Resource.success(moviePagedList)
        `when`(moviesData.data?.size).thenReturn(10)
        val movies = MutableLiveData<Resource<PagedList<Movies>>>()
        movies.value = moviesData

        `when`(movieShowsRepository.getAllMovies()).thenReturn(movies)
        val movieEntities = viewModel.getMoviesData().value?.data
        verify(movieShowsRepository).getAllMovies()
        assertNotNull(movieEntities)
        assertEquals(10, movieEntities?.size)

        viewModel.getMoviesData().observeForever(movieObserver)
        verify(movieObserver).onChanged(moviesData)
    }

    @Test
    fun getTvShowsData() {
        val showData = Resource.success(showPagedList)
        `when`(showData.data?.size).thenReturn(10)
        val shows = MutableLiveData<Resource<PagedList<TvShows>>>()
        shows.value = showData

        `when`(movieShowsRepository.getAllTvShows()).thenReturn(shows)
        val showEntities = viewModel.getTvShowsData().value?.data
        verify(movieShowsRepository).getAllTvShows()
        assertNotNull(showEntities)
        assertEquals(10, showEntities?.size)

        viewModel.getTvShowsData().observeForever(showsObserver)
        verify(showsObserver).onChanged(showData)
    }
}