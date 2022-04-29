package com.yerayyas.mvvm.data.vo

import com.google.gson.annotations.SerializedName

/**
 * Data model for Movie response
 */
data class  MovieResponse(
    val page: Int,
    @SerializedName("results")
    val movieList: List<Movie>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)