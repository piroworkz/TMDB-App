package com.piroworkz.bluelabstmdbapp.usecases

import com.piroworkz.bluelabstmdbapp.data.MovieRepository
import javax.inject.Inject

class RequestTopRatedMoviesUseCase @Inject constructor(private val repository: MovieRepository) {
    suspend operator fun invoke() = repository.requestTopRatedMovies()
}