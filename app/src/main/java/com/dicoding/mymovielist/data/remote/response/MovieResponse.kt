package com.dicoding.mymovielist.data.remote.response

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class MovieResponse(
    var image: String = "",
    var title: String = "",
    var overview: String= "",
    var releaseDate: String= "",
    var genre: String= "",
    var backdrop: String = "",
    var trailer: String = ""
): Parcelable

