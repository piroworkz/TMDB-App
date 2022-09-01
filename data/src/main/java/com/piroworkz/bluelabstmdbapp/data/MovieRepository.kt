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
        roomLocalDataSource.saveTopRatedMovies(remoteDataSource.requestTopRatedMovies())
    }

    suspend fun requestNowPlayingMovies() {
        roomLocalDataSource.saveNowPlayingMovies(remoteDataSource.requestNowPlayingMovies())
    }

    suspend fun countTopRatedMovies(): Int {
        return roomLocalDataSource.countTopRatedMovies()
    }

    suspend fun countNowPlayingMovies(): Int {
        return roomLocalDataSource.countNowPlayingMovies()
    }
}