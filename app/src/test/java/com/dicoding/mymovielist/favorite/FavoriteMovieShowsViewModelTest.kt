package com.dicoding.mymovielist.favorite

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.paging.PositionalDataSource
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.utils.MoviesTvShowsData
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class FavoriteMovieShowsViewModelTest {
    private lateinit var viewModel: FavoriteMovieShowsViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieShowsRepository: MovieShowsRepository

    @Mock
    private lateinit var movieObserver: Observer<PagedList<Movies>>

    @Mock
    private lateinit var tvShowObserver: Observer<PagedList<TvShows>>

    @Before
    fun setUp() {
        viewModel = FavoriteMovieShowsViewModel(movieShowsRepository)
    }

    @Test
    fun getMoviesSuccess() {
        val expected = MutableLiveData<PagedList<Movies>>()
        expected.value = MoviePagedTestDataSources.snapshot(MoviesTvShowsData.generateMoviesData())

        `when`(movieShowsRepository.getFavoriteMovies()).thenReturn(expected)

        viewModel.getFavoriteMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)

        val expectedResult = expected.value
        val actualResult = viewModel.getFavoriteMovies().value
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult?.snapshot(), actualResult?.snapshot())
        assertEquals(expectedResult?.size, actualResult?.size)
    }

    @Test
    fun getMoviesEmpty() {
        val expected = MutableLiveData<PagedList<Movies>>()
        expected.value = MoviePagedTestDataSources.snapshot()

        `when`(movieShowsRepository.getFavoriteMovies()).thenReturn(expected)

        viewModel.getFavoriteMovies().observeForever(movieObserver)
        verify(movieObserver).onChanged(expected.value)

        val actualResultSize = viewModel.getFavoriteMovies().value?.size
        assertTrue("data size = 0, actual size = $actualResultSize", actualResultSize == 0)
    }

    class MoviePagedTestDataSources private constructor(private val items: List<Movies>) : PositionalDataSource<Movies>() {
        companion object {
            fun snapshot(items: List<Movies> = listOf()): PagedList<Movies> {
                return PagedList.Builder(MoviePagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<Movies>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<Movies>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

    @Test
    fun getTvShowsSuccess() {
        val expected = MutableLiveData<PagedList<TvShows>>()
        expected.value = ShowPagedTestDataSources.snapshot(MoviesTvShowsData.generateTvShowsData())

        `when`(movieShowsRepository.getFavoriteTvShows()).thenReturn(expected)

        viewModel.getFavoriteShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(expected.value)

        val expectedResult = expected.value
        val actualResult = viewModel.getFavoriteShow().value
        assertEquals(expectedResult, actualResult)
        assertEquals(expectedResult?.snapshot(), actualResult?.snapshot())
        assertEquals(expectedResult?.size, actualResult?.size)
    }

    @Test
    fun getTvShowsSuccessEmpty() {
        val expected = MutableLiveData<PagedList<TvShows>>()
        expected.value = ShowPagedTestDataSources.snapshot()

        `when`(movieShowsRepository.getFavoriteTvShows()).thenReturn(expected)

        viewModel.getFavoriteShow().observeForever(tvShowObserver)
        verify(tvShowObserver).onChanged(expected.value)

        val actualResultSize = viewModel.getFavoriteShow().value?.size
        assertTrue("data size = 0, actual size = $actualResultSize", actualResultSize == 0)
    }

    class ShowPagedTestDataSources private constructor(private val items: List<TvShows>) : PositionalDataSource<TvShows>() {
        companion object {
            fun snapshot(items: List<TvShows> = listOf()): PagedList<TvShows> {
                return PagedList.Builder(ShowPagedTestDataSources(items), 10)
                    .setNotifyExecutor(Executors.newSingleThreadExecutor())
                    .setFetchExecutor(Executors.newSingleThreadExecutor())
                    .build()
            }
        }

        override fun loadInitial(params: LoadInitialParams, callback: LoadInitialCallback<TvShows>) {
            callback.onResult(items, 0, items.size)
        }

        override fun loadRange(params: LoadRangeParams, callback: LoadRangeCallback<TvShows>) {
            val start = params.startPosition
            val end = params.startPosition + params.loadSize
            callback.onResult(items.subList(start, end))
        }
    }

}