package com.englesoft.movieapp.data.mapper

import com.englesoft.movieapp.data.model.MovieDto
import com.englesoft.movieapp.domain.model.Movie

fun MovieDto.toMovie(): Movie {
    return Movie(
        imdbID = imdbID,
        title = title,
        year = year,
        poster = poster
    )
}