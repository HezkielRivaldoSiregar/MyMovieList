package com.dicoding.mymovielist.detail

import com.dicoding.mymovielist.data.MoviesTvShowsData
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class ShowsDetailViewModelTest {

    private lateinit var viewModel: ShowsDetailViewModel
    private val showsData = MoviesTvShowsData.generateTvShowsData()[0]
    private val title = showsData.title

    @Before
    fun setUp(){
        viewModel = ShowsDetailViewModel()
        viewModel.setSelectedShows(title)
    }

    @Test
    fun getShows() {
        viewModel.setSelectedShows(showsData.title)
        val showsEntity = viewModel.getShows()
        assertNotNull(showsEntity)
        assertEquals(showsData.image, showsEntity.image)
        assertEquals(showsData.title, showsEntity.title)
        assertEquals(showsData.overview, showsEntity.overview)
        assertEquals(showsData.creator, showsEntity.creator)
        assertEquals(showsData.rating, showsEntity.rating)
        assertEquals(showsData.releaseDate, showsEntity.releaseDate)
        assertEquals(showsData.seasons, showsEntity.seasons)
        assertEquals(showsData.genre, showsEntity.genre)
        assertEquals(showsData.status, showsEntity.status)
        assertEquals(showsData.duration, showsEntity.duration)
    }
}