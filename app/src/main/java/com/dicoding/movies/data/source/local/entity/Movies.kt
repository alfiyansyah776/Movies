package com.dicoding.movies.data.source.local.entity

import android.os.Parcelable
import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "moviesentities")
@Parcelize
data class Movies(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "moviesId")
        var moviesId: Int,

        @ColumnInfo(name = "poster")
        var poster: String,

        @ColumnInfo(name = "language")
        var language: String,

        @ColumnInfo(name = "synopsis")
        var synopsis: String,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "aired")
        var aired: String,

        @ColumnInfo(name = "favorite")
        var favorite: Boolean = false,

        @ColumnInfo(name = "score")
        var score: Double
        ) : Parcelable