package com.dicoding.mymovielist.di

import android.content.Context
import com.dicoding.mymovielist.data.JsonHelper
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.remote.RemoteDataSource

object Injection {

    fun provideRepository(context: Context): MovieShowsRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        return MovieShowsRepository.getInstance(remoteDataSource)
    }

}