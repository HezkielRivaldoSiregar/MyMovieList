package com.dicoding.mymovielist.data

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.dicoding.mymovielist.data.db.LocalDataSource
import com.dicoding.mymovielist.data.local.Movies
import com.dicoding.mymovielist.data.local.TvShows
import com.dicoding.mymovielist.data.remote.RemoteDataSource
import com.dicoding.mymovielist.data.remote.response.ApiResponse
import com.dicoding.mymovielist.utils.AppExecutors
import com.dicoding.mymovielist.vo.Resource

class MovieShowsRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieShowsDataSource {

    companion object {
        @Volatile
        private var instance: MovieShowsRepository? = null
        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieShowsRepository =
            instance ?: synchronized(this) {
                instance ?: MovieShowsRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<Movies>>> {
        return object :
            NetworkBoundResource<PagedList<Movies>, List<Movies>>(appExecutors) {

            override fun loadFromDB(): LiveData<PagedList<Movies>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<Movies>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<Movies>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<Movies>) {
                val movieList = ArrayList<Movies>()

                for (response in data) {
                    val movie = Movies(
                        response.id,
                        response.image,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.genre,
                        response.backdrop,
                        response.trailer,
                        response.duration
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getMoviesById(movieId: Int): LiveData<Resource<Movies>> {
        return object : NetworkBoundResource<Movies, List<Movies>>(appExecutors) {

            override fun loadFromDB(): LiveData<Movies> =
                localDataSource.getMovieById(movieId)

            override fun shouldFetch(data: Movies?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<Movies>>> =
                remoteDataSource.getAllMovies()

            override fun saveCallResult(data: List<Movies>) {
                val movieList = ArrayList<Movies>()

                for (response in data) {
                    val movie = Movies(
                        response.id,
                        response.image,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.genre,
                        response.backdrop,
                        response.trailer,
                        response.duration
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovie(movieList)
            }
        }.asLiveData()
    }

    override fun getAllTvShows(): LiveData<Resource<PagedList<TvShows>>> {
        return object :
            NetworkBoundResource<PagedList<TvShows>, List<TvShows>>(appExecutors) {

            override fun loadFromDB(): LiveData<PagedList<TvShows>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getShow(), config).build()
            }

            override fun shouldFetch(data: PagedList<TvShows>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<TvShows>>> =
                remoteDataSource.getAllShows()

            override fun saveCallResult(data: List<TvShows>) {
                val tvShowList = ArrayList<TvShows>()

                for (response in data) {
                    val tvShow = TvShows(
                        response.id,
                        response.image,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.seasons,
                        response.genre,
                        response.backdrop,
                        response.trailer,
                        response.duration
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun getTvShowsById(tvShowId: Int): LiveData<Resource<TvShows>> {
        return object : NetworkBoundResource<TvShows, List<TvShows>>(appExecutors) {

            override fun loadFromDB(): LiveData<TvShows> =
                localDataSource.getShowById(tvShowId)

            override fun shouldFetch(data: TvShows?): Boolean =
                data == null

            override fun createCall(): LiveData<ApiResponse<List<TvShows>>> =
                remoteDataSource.getAllShows()

            override fun saveCallResult(data: List<TvShows>) {
                val tvShowList = ArrayList<TvShows>()

                for (response in data) {
                    val tvShow = TvShows(
                        response.id,
                        response.image,
                        response.title,
                        response.overview,
                        response.releaseDate,
                        response.seasons,
                        response.genre,
                        response.backdrop,
                        response.trailer,
                        response.duration
                    )
                    tvShowList.add(tvShow)
                }

                localDataSource.insertShow(tvShowList)
            }
        }.asLiveData()
    }

    override fun setFavoriteMovie(movie: Movies, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteMovie(movie, state) }
    }

    override fun setFavoriteTvShow(tvShow: TvShows, state: Boolean) {
        appExecutors.diskIO().execute { localDataSource.setFavoriteTvShow(tvShow, state) }
    }

    override fun getFavoriteMovies(): LiveData<PagedList<Movies>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTvShows(): LiveData<PagedList<TvShows>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteShow(), config).build()
    }

}