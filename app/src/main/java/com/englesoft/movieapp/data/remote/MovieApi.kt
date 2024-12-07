package com.englesoft.movieapp.data.remote

import com.englesoft.movieapp.data.model.MovieDetailsDto
import com.englesoft.movieapp.data.model.MovieListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("/")
    suspend fun getMovies(
        @Query("s") query: String,
        @Query("y") year: String?
    ): MovieListResponse

    @GET("/")
    suspend fun getMovieDetails(
        @Query("i") imdbID: String
    ): MovieDetailsDto
}