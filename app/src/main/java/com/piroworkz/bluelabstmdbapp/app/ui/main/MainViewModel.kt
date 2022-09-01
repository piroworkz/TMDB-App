package com.piroworkz.bluelabstmdbapp.app.ui.main

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.piroworkz.bluelabstmdbapp.domain.Movie
import com.piroworkz.bluelabstmdbapp.usecases.*
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
    private val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    private val countNowPlayingMoviesUseCase: CountNowPlayingMoviesUseCase,
    private val countTopRatedMoviesUseCase: CountTopRatedMoviesUseCase
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
        val loading: Boolean = false,
    )

    private fun getTopRated() {
        loadData {
            getTopRatedMoviesUseCase().collect { movies: List<Movie> ->
                _state.update { it.copy(topRatedMovies = movies) }
            }
        }
    }

    private fun getNowPlaying() {
        loadData {
            getNowPlayingMoviesUseCase().collect { movies: List<Movie> ->
                _state.update { it.copy(nowPlayingMovies = movies) }
            }
        }
    }

    private fun requestNowPlaying() {
        loadData {
            if (countNowPlayingMoviesUseCase() == 0) {
                requestNowPlayingMoviesUseCase()
            }
        }
    }

    private fun requestTopRated() {
        loadData {
            if (countTopRatedMoviesUseCase() == 0) {
                requestTopRatedMoviesUseCase()
            }
        }
    }

    private fun loadData(block: suspend () -> Unit) {
        try {
            _state.update { it.copy(loading = true) }
            viewModelScope.launch {
                block()
            }
        } catch (e: Exception) {
            e.message?.logMessage()
        } finally {
            _state.update { it.copy(loading = false) }
        }
    }
}

fun String.logMessage(name: String = javaClass.simpleName) {
    Log.d("<-- $name", this)
}
