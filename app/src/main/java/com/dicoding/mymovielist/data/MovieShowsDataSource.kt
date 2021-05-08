package com.dicoding.mymovielist.data

import androidx.lifecycle.LiveData
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

interface MovieShowsDataSource {
        fun getAllMovies(): LiveData<List<Movies>>
        fun getAllShows(): LiveData<List<TvShows>>
}