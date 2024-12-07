package com.englesoft.movieapp.presentation.details

import com.englesoft.movieapp.domain.model.MovieDetails

data class DetailsScreenState(
    val movie: MovieDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
