package com.dicoding.mymovielist.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.*
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows

@Dao
interface MovieShowDao {
    @Query("SELECT * FROM movieEntities")
    fun getMovies(): DataSource.Factory<Int, Movies>

    @Query("SELECT * FROM showEntities")
    fun getShow(): DataSource.Factory<Int, TvShows>

    @Transaction
    @Query("SELECT * FROM movieEntities WHERE movieEntities.id = :id")
    fun getMovieById(id : Int):LiveData<Movies>

    @Query("SELECT * FROM showEntities WHERE showEntities.id = :id")
    fun getShowById(id : Int):LiveData<TvShows>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertMovie(movies:List<Movies>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertShow(show:List<TvShows>)

    @Update
    fun updateMovie(movie: Movies)

    @Update
    fun updateShow(show : TvShows)

    @Query("SELECT * FROM movieEntities WHERE favorite = 1")
    fun getFavoriteMovie(): DataSource.Factory<Int, Movies>

    @Query("SELECT * FROM showEntities WHERE favorite = 1")
    fun getFavoriteShow(): DataSource.Factory<Int, TvShows>
}