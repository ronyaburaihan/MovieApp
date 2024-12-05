package com.englesoft.movieapp.presentation.home

import com.englesoft.movieapp.data.model.Movie

data class HomeScreenState(
    val movies: List<Movie>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
