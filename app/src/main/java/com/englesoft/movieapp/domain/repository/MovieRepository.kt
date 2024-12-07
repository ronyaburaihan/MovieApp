package com.englesoft.movieapp.domain.repository

import com.englesoft.movieapp.domain.model.Movie
import com.englesoft.movieapp.domain.model.MovieDetails
import com.englesoft.movieapp.util.Resource

interface MovieRepository {
    suspend fun getMovies(query: String, year: String?, page: Int?): Resource<List<Movie>>
    suspend fun getMovieDetails(imdbID: String): Resource<MovieDetails>
}
