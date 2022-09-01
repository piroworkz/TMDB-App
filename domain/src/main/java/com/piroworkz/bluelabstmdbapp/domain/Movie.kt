package com.piroworkz.bluelabstmdbapp.domain

data class Movie(
    val movieId: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: String,
    val overview: String,
    val backDropPath: String,
    val type: MovieType
)

enum class MovieType{
    TOP, PLAYING
}
