package com.dicoding.mymovielist.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.paging.DataSource
import com.dicoding.mymovielist.data.db.LocalDataSource
import com.dicoding.mymovielist.data.db.MovieShowDao
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.data.remote.RemoteDataSource
import com.dicoding.mymovielist.utils.AppExecutors
import com.dicoding.mymovielist.utils.MoviesTvShowsData
import com.dicoding.mymovielist.vo.Resource
import com.nhaarman.mockitokotlin2.doNothing
import com.nhaarman.mockitokotlin2.verify
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito
import org.mockito.Mockito.*
import java.util.concurrent.Executors

class MovieShowsRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)
    private val dao = mock(MovieShowDao::class.java)
    private val movieCatalogueRepository = FakeMovieShowsRepository(remote, local, appExecutors)

    private val movieResponse = MoviesTvShowsData.generateMoviesData()
    private val showResponse = MoviesTvShowsData.generateTvShowsData()

    @Test
    fun getAllMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movies>
        `when`(local.getMovies()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getAllMovies()

        val movieEntities = Resource.success(PagedListUtils.mockPagedList(MoviesTvShowsData.generateMoviesData()))
        verify(local).getMovies()
        assertNotNull(movieEntities)
        assertEquals(movieResponse.size.toLong(), movieEntities.data?.size?.toLong())
    }

    @Test
    fun getAllTvShows(){
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShows>
        `when`(local.getShow()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getAllTvShows()

        val showEntities = Resource.success(PagedListUtils.mockPagedList(MoviesTvShowsData.generateTvShowsData()))
        verify(local).getShow()
        assertNotNull(showEntities)
        assertEquals(showResponse.size.toLong(), showEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteMovies() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, Movies>
        `when`(local.getFavoriteMovie()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoriteMovies()

        val favoriteMovieEntities = Resource.success(PagedListUtils.mockPagedList(MoviesTvShowsData.generateMoviesData()))
        verify(local).getFavoriteMovie()
        assertNotNull(favoriteMovieEntities)
        assertEquals(movieResponse.size.toLong(), favoriteMovieEntities.data?.size?.toLong())
    }

    @Test
    fun getFavoriteTvShows() {
        val dataSourceFactory = mock(DataSource.Factory::class.java) as DataSource.Factory<Int, TvShows>
        `when`(local.getFavoriteShow()).thenReturn(dataSourceFactory)
        movieCatalogueRepository.getFavoriteTvShows()

        val favoriteShowEntities = Resource.success(PagedListUtils.mockPagedList(MoviesTvShowsData.generateTvShowsData()))
        verify(local).getFavoriteShow()
        assertNotNull(favoriteShowEntities)
        assertEquals(showResponse.size.toLong(), favoriteShowEntities.data?.size?.toLong())
    }

    @Test
    fun setFavoriteMovie() {
        val localData = LocalDataSource.getInstance(dao)
        val movieData = MoviesTvShowsData.generateMoviesData()[0]
        val expectedData = movieData.copy(favorited = true)

        doNothing().`when`(dao).updateMovie(expectedData)
        localData.setFavoriteMovie(movieData, true)

        verify(dao, Mockito.times(1)).updateMovie(expectedData)
    }

    @Test
    fun setFavoriteTvShow() {
        val tvShowData = MoviesTvShowsData.generateTvShowsData()[0]
        val newState = !tvShowData.favorited

        `when`(appExecutors.diskIO()).thenReturn(Executors.newSingleThreadExecutor())
        local.setFavoriteTvShow(tvShowData, newState)

        movieCatalogueRepository.setFavoriteTvShow(tvShowData, newState)
        verify(local, times(1)).setFavoriteTvShow(tvShowData,newState)
        verifyNoMoreInteractions(local)
    }

}