package com.dicoding.mymovielist.data.remote.response

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MovieResponse(
    var image: String = "",
    var title: String = "",
    var overview: String= "",
    var director: String= "",
    var rating: String= "",
    var releaseDate: String= "",
    var genre: String= "",
    val status: String= "",
    val duration: String = "",
): Parcelable

