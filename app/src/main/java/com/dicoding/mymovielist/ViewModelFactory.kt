package com.dicoding.mymovielist

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.mymovielist.data.MovieShowsRepository
import com.dicoding.mymovielist.detail.MoviesShowsDetailViewModel
import com.dicoding.mymovielist.di.Injection
import com.dicoding.mymovielist.main.MoviesShowsViewModel

class ViewModelFactory private constructor(private val mMovieShowsRepository: MovieShowsRepository) : ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory = instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(MoviesShowsViewModel::class.java) -> {
                MoviesShowsViewModel(mMovieShowsRepository) as T
            }
            modelClass.isAssignableFrom(MoviesShowsDetailViewModel::class.java) -> {
                MoviesShowsDetailViewModel(mMovieShowsRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }

    }

}