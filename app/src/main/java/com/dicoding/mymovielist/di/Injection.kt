package com.dicoding.mymovielist.di

import android.content.Context
import com.dicoding.mymovielist.utils.JsonHelper
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.data.db.LocalDataSource
import com.dicoding.mymovielist.data.db.MovieShowDatabase
import com.dicoding.mymovielist.data.remote.RemoteDataSource
import com.dicoding.mymovielist.utils.AppExecutors

object Injection {

    fun provideRepository(context: Context): MovieShowsRepository {

        val database = MovieShowDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.movieShowDao())
        val appExecutors = AppExecutors()

        return MovieShowsRepository.getInstance(remoteDataSource, localDataSource, appExecutors)
    }

}