package com.piroworkz.bluelabstmdbapp.usecases

import com.piroworkz.bluelabstmdbapp.data.MovieRepository
import javax.inject.Inject

class FindMovieByIdUseCase @Inject constructor(private val repository: MovieRepository) {
    operator fun invoke(movieId: Int) = repository.findMovieById(movieId)
}