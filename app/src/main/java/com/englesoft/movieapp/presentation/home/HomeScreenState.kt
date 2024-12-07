package com.englesoft.movieapp.presentation.home

import com.englesoft.movieapp.domain.model.Movie

data class HomeScreenState(
    val movieCarousels: List<Movie>? = null,
    val batmanRails: List<Movie>? = null,
    val latestRails: List<Movie>? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)
