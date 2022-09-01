package com.piroworkz.bluelabstmdbapp.data

import com.piroworkz.bluelabstmdbapp.data.datasource.LocalDataSource
import com.piroworkz.bluelabstmdbapp.data.datasource.MoviesRemoteDataSource
import com.piroworkz.bluelabstmdbapp.domain.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val remoteDataSource: MoviesRemoteDataSource,
    private val roomLocalDataSource: LocalDataSource,
) {

    val topRatedMovies: Flow<List<Movie>> = roomLocalDataSource.topRatedMovies
    val nowPlayingMovies: Flow<List<Movie>> = roomLocalDataSource.nowPlayingMovies

    fun findMovieById(movieId: Int): Flow<Movie> {
        return roomLocalDataSource.findMovieById(movieId)
    }

    suspend fun requestTopRatedMovies() {
        if (roomLocalDataSource.topRatedIsEmpty()) {
            roomLocalDataSource.saveTopRatedMovies(remoteDataSource.requestTopRatedMovies())
        }

    }

    suspend fun requestNowPlayingMovies() {
        if (roomLocalDataSource.nowPlayingIsEmpty()) {
            roomLocalDataSource.saveNowPlayingMovies(remoteDataSource.requestNowPlayingMovies())
        }
    }
}