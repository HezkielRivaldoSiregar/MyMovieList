package com.dicoding.mymovielist.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

@Database(entities = [Movies::class, TvShows::class],
version = 1,
exportSchema = false)
abstract class MovieShowDatabase : RoomDatabase() {
    abstract fun movieShowDao(): MovieShowDao

    companion object {

        @Volatile
        private var INSTANCE: MovieShowDatabase? = null

        fun getInstance(context: Context): MovieShowDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    MovieShowDatabase::class.java,
                    "Favorite.db"
                ).build()
            }
    }
}