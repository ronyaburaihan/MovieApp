package com.englesoft.movieapp.data.repository

import com.englesoft.movieapp.data.mapper.toMovie
import com.englesoft.movieapp.data.model.MovieDetails
import com.englesoft.movieapp.data.remote.MovieApi
import com.englesoft.movieapp.domain.model.Movie
import com.englesoft.movieapp.domain.repository.MovieRepository
import com.englesoft.movieapp.util.Resource
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val movieApi: MovieApi
) : MovieRepository {
    override suspend fun getMovies(query: String, year: String?): Resource<List<Movie>> {
        return try {
            Resource.Success(
                data = movieApi.getMovies(
                    query = query,
                    year = year
                ).movieDtos.map { it.toMovie() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

    override suspend fun getMovieDetails(imdbID: String): Resource<MovieDetails> {
        return try {
            Resource.Success(
                data = movieApi.getMovieDetails(
                    imdbID = imdbID,
                )
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.message ?: "An unknown error occurred.")
        }
    }

}