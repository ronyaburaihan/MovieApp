package com.englesoft.movieapp.domain.usecase

import com.englesoft.movieapp.domain.repository.MovieRepository

class GetMoviesUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(query: String, year: String?, page: Int?) =
        repository.getMovies(query, year, page)
}