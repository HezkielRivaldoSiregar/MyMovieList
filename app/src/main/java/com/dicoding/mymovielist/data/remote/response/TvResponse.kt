package com.dicoding.mymovielist.data.remote.response

import android.os.Parcelable

@kotlinx.parcelize.Parcelize
data class TvResponse (
    var image : String,
    var title : String,
    var overview: String,
    var releaseDate: String,
    var seasons: String,
    var genre: String,
    var backdrop: String,
    var trailer: String
    ): Parcelable