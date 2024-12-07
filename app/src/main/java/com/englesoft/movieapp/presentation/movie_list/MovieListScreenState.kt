package com.englesoft.movieapp.presentation.movie_list

import com.englesoft.movieapp.domain.model.Movie

data class MovieListScreenState(
    val movies: List<Movie>? = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null,
    val currentPage: Int = 1
)
