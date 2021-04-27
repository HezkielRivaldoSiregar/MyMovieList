package com.dicoding.mymovielist.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movies(
    var image: Int,
    var title: String,
    var overview: String,
    var director: String,
    var rating: String,
    var releaseDate: String,
    var genre: String,
    val status: String,
    val duration: String,
): Parcelable