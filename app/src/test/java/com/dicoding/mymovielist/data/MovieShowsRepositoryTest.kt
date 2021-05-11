package com.dicoding.mymovielist.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.dicoding.mymovielist.data.local.MoviesTvShowsData
import com.dicoding.mymovielist.data.remote.RemoteDataSource
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock

class MovieShowsRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val movieShowsRepository = FakeMovieShowsRepository(remote)

    private val movieResponse = MoviesTvShowsData.generateMoviesData()
    private val showResponse = MoviesTvShowsData.generateTvShowsData()

    @Test
    fun getAllMovies(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponse)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieShowsRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        Assert.assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getAllTvShows(){
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvShowsCallback)
                .onAllTvShowsReceived(showResponse)
            null
        }.`when`(remote).getAllShows(any())
        val tvshowEntities = LiveDataTestUtil.getValue(movieShowsRepository.getAllShows())
        verify(remote).getAllShows(any())
        Assert.assertNotNull(tvshowEntities)
        assertEquals(showResponse.size.toLong(), tvshowEntities.size.toLong())
    }

}