package com.dicoding.mymovielist.data.local

data class Movies(
    var image: String = "",
    var title: String = "",
    var overview: String= "",
    var director: String= "",
    var rating: String= "",
    var releaseDate: String= "",
    var genre: String= "",
    val status: String= "",
    val duration: String= ""
)