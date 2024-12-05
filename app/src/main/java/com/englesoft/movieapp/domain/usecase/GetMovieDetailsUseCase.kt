package com.englesoft.movieapp.domain.usecase

import com.englesoft.movieapp.domain.repository.MovieRepository

class GetMovieDetailsUseCase(private val repository: MovieRepository) {
    suspend operator fun invoke(imdbID: String) = repository.getMovieDetails(imdbID)
}