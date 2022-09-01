package com.piroworkz.bluelabstmdbapp.data.datasource

import com.piroworkz.bluelabstmdbapp.domain.Movie
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {
    val topRatedMovies: Flow<List<Movie>>
    val nowPlayingMovies: Flow<List<Movie>>
    fun findMovieById(movieId: Int): Flow<Movie>

    suspend fun topRatedIsEmpty(): Boolean

    suspend fun nowPlayingIsEmpty(): Boolean

    suspend fun saveTopRatedMovies(list: List<Movie>)

    suspend fun saveNowPlayingMovies(list: List<Movie>)

    suspend fun countTopRatedMovies(): Int

    suspend fun countNowPlayingMovies(): Int

}