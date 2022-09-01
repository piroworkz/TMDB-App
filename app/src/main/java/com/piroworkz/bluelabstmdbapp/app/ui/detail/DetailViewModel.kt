package com.piroworkz.bluelabstmdbapp.app.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.bluelabstmdbapp.app.ui.detail.di.MovieId
import com.piroworkz.bluelabstmdbapp.domain.Movie
import com.piroworkz.bluelabstmdbapp.usecases.FindMovieByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    @MovieId private val movieId: Int,
    private val findMovieByIdUseCase: FindMovieByIdUseCase
) :
    ViewModel() {

    private val _state = MutableStateFlow(DetailState())
    val state get() = _state.asStateFlow()

    init {
        viewModelScope.launch {
            findMovieByIdUseCase(movieId).collect { movie ->
                _state.update { it.copy(movie = movie) }
            }
        }
    }

    data class DetailState(
        val movie: Movie? = null
    )
}