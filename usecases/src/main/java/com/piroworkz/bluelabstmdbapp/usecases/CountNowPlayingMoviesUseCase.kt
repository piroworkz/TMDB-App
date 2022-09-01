package com.piroworkz.bluelabstmdbapp.usecases

import com.piroworkz.bluelabstmdbapp.data.MovieRepository
import javax.inject.Inject

class CountNowPlayingMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke(): Int = repository.countNowPlayingMovies()
}