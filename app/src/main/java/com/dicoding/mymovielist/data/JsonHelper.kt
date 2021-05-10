package com.dicoding.mymovielist.data

import android.content.Context
import com.dicoding.mymovielist.data.remote.response.MovieResponse
import com.dicoding.mymovielist.data.remote.response.TvResponse
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException

class JsonHelper(private val context: Context){
    private fun parsingFileToString(fileName: String): String? {
        return try {
            val `is` = context.assets.open(fileName)
            val buffer = ByteArray(`is`.available())
            `is`.read(buffer)
            `is`.close()
            String(buffer)

        } catch (ex: IOException) {
            ex.printStackTrace()
            null
        }
    }

    fun loadMovies(): List<MovieResponse> {
        val movieslist = ArrayList<MovieResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("MovieJSON.json").toString())
            val listArray = responseObject.getJSONArray("movies")
            for (i in 0 until listArray.length()) {
                val movie = listArray.getJSONObject(i)

                val image = movie.getString("images")
                val title = movie.getString("title")
                val overview = movie.getString("overview")
                val director = movie.getString("director")
                val rating = movie.getString("rating")
                val releaseDate = movie.getString("releaseDate")
                val genre = movie.getString("genre")
                val status = movie.getString("status")
                val duration = movie.getString("duration")

                val movieResponse = MovieResponse(image,title,overview,director, rating, releaseDate, genre, status, duration)
                movieslist.add(movieResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return movieslist
    }

    fun loadTvshows(): List<TvResponse> {
        val showslist = ArrayList<TvResponse>()
        try {
            val responseObject = JSONObject(parsingFileToString("TvShowsJSON.json").toString())
            val listArray = responseObject.getJSONArray("shows")
            for (i in 0 until listArray.length()) {
                val tvShow = listArray.getJSONObject(i)

                val image = tvShow.getString("images")
                val title = tvShow.getString("title")
                val overview = tvShow.getString("overview")
                val creator = tvShow.getString("creator")
                val rating = tvShow.getString("rating")
                val releaseDate = tvShow.getString("releaseDate")
                val seasons = tvShow.getString("seasons")
                val genre = tvShow.getString("genre")
                val status = tvShow.getString("status")
                val duration = tvShow.getString("duration")

                val tvshowResponse = TvResponse(image,title, overview, creator, rating, releaseDate, seasons, genre, status, duration)
                showslist.add(tvshowResponse)
            }
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return showslist
    }
}