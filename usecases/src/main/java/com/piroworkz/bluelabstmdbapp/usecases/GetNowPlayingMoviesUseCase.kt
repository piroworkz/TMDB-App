package com.piroworkz.bluelabstmdbapp.usecases

import com.piroworkz.bluelabstmdbapp.data.MovieRepository
import com.piroworkz.bluelabstmdbapp.domain.Movie
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(): Flow<List<Movie>> = repository.nowPlayingMovies
}