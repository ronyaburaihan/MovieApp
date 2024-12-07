package com.englesoft.movieapp.domain.model

data class MovieDetails(
    val title: String,
    val year: String,
    val rated: String,
    val genre: String,
    val plot: String,
    val poster: String,
    val boxOffice: String,
    val streamUrl: String,
    val ratings: List<Rating>
)