package com.dicoding.mymovielist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TvShows (
    var image : Int,
    var title : String,
    var overview: String,
    var creator: String,
    var rating: String,
    var releaseDate: String,
    var seasons: String,
    var genre: String,
    val status: String,
    val duration: String
):Parcelable