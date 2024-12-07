package com.englesoft.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class MovieListResponse(
    val totalResults: String,
    @SerializedName("Response")
    val response: String,
    @SerializedName("Search")
    val movieDtos: List<MovieDto>
)
