package com.piroworkz.bluelabstmdbapp.app.data.local.datasource

import com.piroworkz.bluelabstmdbapp.app.data.local.DbMovie
import com.piroworkz.bluelabstmdbapp.app.data.local.MovieDao
import com.piroworkz.bluelabstmdbapp.data.datasource.LocalDataSource
import com.piroworkz.bluelabstmdbapp.domain.Movie
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RoomLocalDataSource @Inject constructor(private val dao: MovieDao) : LocalDataSource {

    override val topRatedMovies: Flow<List<Movie>> =
        dao.getTopRated().map { it.map { movie -> movie.toDomain() } }

    override val nowPlayingMovies: Flow<List<Movie>> =
        dao.getNowPlaying().map { it.map { movie -> movie.toDomain() } }

    override fun findMovieById(movieId: Int): Flow<Movie> {
        return dao.findMovieById(movieId).map { it.toDomain() }
    }

    override suspend fun topRatedIsEmpty(): Boolean {
        return dao.countTopRated() == 0
    }

    override suspend fun nowPlayingIsEmpty(): Boolean {
        return dao.countNowPlaying() == 0
    }

    override suspend fun saveTopRatedMovies(list: List<Movie>) {
        dao.saveTopRatedMovies(list.map { it.toDbMovie() })
    }

    override suspend fun saveNowPlayingMovies(list: List<Movie>) {
        dao.saveNowPlayingMovies(list.map { it.toDbMovie() })
    }

    override suspend fun countTopRatedMovies(): Int = dao.countTopRated()

    override suspend fun countNowPlayingMovies(): Int = dao.countNowPlaying()

    private fun DbMovie.toDomain(): Movie {
        return Movie(
            movieId = movieId,
            posterPath = posterPath,
            title = title,
            voteAverage = voteAverage,
            overview = overview,
            backDropPath = backDropPath,
            type = type
        )
    }

    private fun Movie.toDbMovie(): DbMovie {
        return DbMovie(
            movieId = movieId,
            posterPath = posterPath,
            title = title,
            voteAverage = voteAverage,
            overview = overview,
            backDropPath = backDropPath,
            type = type
        )
    }
}