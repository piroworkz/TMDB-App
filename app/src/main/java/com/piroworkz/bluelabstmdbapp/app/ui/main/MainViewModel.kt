package com.piroworkz.bluelabstmdbapp.app.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.bluelabstmdbapp.domain.Movie
import com.piroworkz.bluelabstmdbapp.usecases.GetNowPlayingMoviesUseCase
import com.piroworkz.bluelabstmdbapp.usecases.GetTopRatedMoviesUseCase
import com.piroworkz.bluelabstmdbapp.usecases.RequestNowPlayingMoviesUseCase
import com.piroworkz.bluelabstmdbapp.usecases.RequestTopRatedMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val requestTopRatedMoviesUseCase: RequestTopRatedMoviesUseCase,
    private val requestNowPlayingMoviesUseCase: RequestNowPlayingMoviesUseCase,
    private val getTopRatedMoviesUseCase: GetTopRatedMoviesUseCase,
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase
) : ViewModel() {

    private val _state = MutableStateFlow(MainState())
    val state get() = _state.asStateFlow()

    init {
        requestTopRated()
        requestNowPlaying()
        getTopRated()
        getNowPlaying()
    }

    data class MainState(
        val topRatedMovies: List<Movie>? = null,
        val nowPlayingMovies: List<Movie>? = null,
        val searchBoxValue: String? = null
    )

    private fun getTopRated() {
        viewModelScope.launch {
            getTopRatedMoviesUseCase().collect { movies: List<Movie> ->
                _state.update { it.copy(topRatedMovies = movies) }
            }
        }
    }

    private fun getNowPlaying() {
        viewModelScope.launch {
            getNowPlayingMoviesUseCase().collect { movies: List<Movie> ->
                _state.update { it.copy(nowPlayingMovies = movies) }
            }
        }
    }

    private fun requestNowPlaying() {
        viewModelScope.launch { requestNowPlayingMoviesUseCase() }
    }

    private fun requestTopRated() {
        viewModelScope.launch { requestTopRatedMoviesUseCase() }
    }
}