package com.yerayyas.mvvm.data.vo

import com.google.gson.annotations.SerializedName

/**
 * Data model for Movie details
 */

data class MovieDetails(
    val id: Int,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    val title: String,
    @SerializedName("vote_average")
    val rating: Double
)