package com.piroworkz.bluelabstmdbapp.app.data.server

import com.piroworkz.bluelabstmdbapp.app.data.server.model.Result
import com.piroworkz.bluelabstmdbapp.app.di.ApiKey
import com.piroworkz.bluelabstmdbapp.data.datasource.MoviesRemoteDataSource
import com.piroworkz.bluelabstmdbapp.domain.Movie
import com.piroworkz.bluelabstmdbapp.domain.MovieType
import javax.inject.Inject

class MoviesServerDataSource @Inject constructor(
    @ApiKey private val apiKey: String,
    private val service: MoviesService
) : MoviesRemoteDataSource {
    override suspend fun requestTopRatedMovies(): List<Movie> {
        return service.getTopRatedMovies(apiKey).results.map { it.toTopMovie() }
    }

    override suspend fun requestNowPlayingMovies(): List<Movie> {
        return service.getNowPlayingMovies(apiKey).results.map { it.toNowMovie() }
    }

    private fun Result.toTopMovie(): Movie {
        return Movie(
            movieId = id,
            posterPath = "https://image.tmdb.org/t/p/w185/$poster_path",
            title = title,
            voteAverage = vote_average.toString(),
            overview = overview,
            backDropPath = "https://image.tmdb.org/t/p/w780/$backdrop_path",
            type = MovieType.TOP
        )
    }

    private fun Result.toNowMovie(): Movie {
        return Movie(
            movieId = id,
            posterPath = "https://image.tmdb.org/t/p/w185/$poster_path",
            title = title,
            voteAverage = vote_average.toString(),
            overview = overview,
            backDropPath = "https://image.tmdb.org/t/p/w780/$backdrop_path",
            type = MovieType.PLAYING
        )
    }

}