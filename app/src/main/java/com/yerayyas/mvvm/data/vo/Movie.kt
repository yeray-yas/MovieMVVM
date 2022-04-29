package com.yerayyas.mvvm.data.vo

import com.google.gson.annotations.SerializedName

/**
 * Data model for our movies
 */
data class Movie(
    val id: Int,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    val overview: String
)