package com.dicoding.mymovielist.data.local

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "showEntities")
@Parcelize
data class TvShows (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int = 0,

    @ColumnInfo(name = "image")
    var image : String,

    @ColumnInfo(name = "title")
    var title : String,

    @ColumnInfo(name = "overview")
    var overview: String,

    @ColumnInfo(name = "releaseDate")
    var releaseDate: String,

    @ColumnInfo(name = "seasons")
    var seasons: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "backdrop")
    var backdrop: String,

    @ColumnInfo(name = "trailer")
    var trailer: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "favorite")
    var favorited: Boolean = false
):Parcelable