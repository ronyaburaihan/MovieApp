package com.englesoft.movieapp.data.mapper

import com.englesoft.movieapp.BuildConfig
import com.englesoft.movieapp.data.model.MovieDetailsDto
import com.englesoft.movieapp.data.model.MovieDto
import com.englesoft.movieapp.data.model.RatingDto
import com.englesoft.movieapp.domain.model.Movie
import com.englesoft.movieapp.domain.model.MovieDetails
import com.englesoft.movieapp.domain.model.Rating

fun MovieDto.toMovie(): Movie {
    return Movie(
        imdbID = imdbID,
        title = title,
        year = year,
        poster = poster
    )
}

fun RatingDto.toRating(): Rating {
    return Rating(
        source = source,
        value = value
    )
}

fun MovieDetailsDto.toMovieDetails(): MovieDetails {
    return MovieDetails(
        title = title,
        year = year,
        rated = rated,
        genre = "Genre: $genre",
        plot = "Plot: $plot",
        poster = poster,
        boxOffice = "Box Office: $boxOffice",
        streamUrl = BuildConfig.STREAM_URL,
        ratings = ratings.map { it.toRating() }
    )
}