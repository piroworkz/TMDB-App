package com.piroworkz.bluelabstmdbapp.data.datasource

import com.piroworkz.bluelabstmdbapp.domain.Movie

interface MoviesRemoteDataSource {
    suspend fun requestTopRatedMovies(): List<Movie>

    suspend fun requestNowPlayingMovies(): List<Movie>
}