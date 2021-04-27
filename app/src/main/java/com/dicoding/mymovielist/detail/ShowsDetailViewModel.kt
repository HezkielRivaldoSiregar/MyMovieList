package com.dicoding.mymovielist.detail

import androidx.lifecycle.ViewModel
import com.dicoding.mymovielist.data.MoviesTvShowsData
import com.dicoding.mymovielist.data.TvShows

class ShowsDetailViewModel: ViewModel() {
    private lateinit var title: String

    fun setSelectedShows(title: String){
        this.title = title
    }

    fun getShows(): TvShows {
        lateinit var shows: TvShows
        val showsEntities = MoviesTvShowsData.generateTvShowsData()
        for(showsEntity in showsEntities){
            if(showsEntity.title == title){
                shows = showsEntity
            }
        }
        return shows
    }

}