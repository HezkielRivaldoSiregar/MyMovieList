package com.dicoding.mymovielist.data.local

data class TvShows (
    var image : String,
    var title : String,
    var overview: String,
    var creator: String,
    var rating: String,
    var releaseDate: String,
    var seasons: String,
    var genre: String,
    val status: String,
    val duration: String
)