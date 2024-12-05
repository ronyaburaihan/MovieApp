package com.englesoft.movieapp.data.model

import com.google.gson.annotations.SerializedName

data class Movie(
    val imdbID: String,
    @SerializedName("Title")
    val title: String,
    @SerializedName("Year")
    val year: String,
    @SerializedName("Poster")
    val poster: String
)
