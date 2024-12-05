package com.englesoft.movieapp.domain.repository

import com.englesoft.movieapp.data.model.Movie
import com.englesoft.movieapp.data.model.MovieDetails
import com.englesoft.movieapp.util.Resource

interface MovieRepository {
    suspend fun getMovies(query: String, year: String?): Resource<List<Movie>>
    suspend fun getMovieDetails(imdbID: String): Resource<MovieDetails>
}
