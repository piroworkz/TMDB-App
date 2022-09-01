package com.piroworkz.bluelabstmdbapp.app.ui.main

import android.widget.SearchView
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.bluelabstmdbapp.domain.Movie

class MainState(val findNavController: NavController) {

    fun queryListener(
        st: MainViewModel.MainState,
        topRatedMovie: (List<Movie>) -> Unit,
        nowPlayingMovie: (List<Movie>) -> Unit
    ) = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }
        override fun onQueryTextChange(newText: String?): Boolean {
            submitLists(newText, st, topRatedMovie, nowPlayingMovie)
            return true
        }
    }

    private fun submitLists(
        newText: String?,
        st: MainViewModel.MainState,
        topRatedMovie: (List<Movie>) -> Unit,
        nowPlayingMovie: (List<Movie>) -> Unit
    ) {
        if (newText?.isNotEmpty() == true) {
            val filteredTopMovies = st.topRatedMovies?.filterList(newText)
            val filteredNowPlayingMovies = st.nowPlayingMovies?.filterList(newText)

            if (filteredTopMovies?.isNotEmpty() == true) {
                topRatedMovie(filteredTopMovies)
            }
            if (filteredNowPlayingMovies?.isNotEmpty() == true) {
                nowPlayingMovie(filteredNowPlayingMovies)
            }
        } else {
            st.topRatedMovies?.let { topRatedMovie(it) }
            st.nowPlayingMovies?.let { nowPlayingMovie(it) }
        }
    }

    private fun List<Movie>.filterList(filter: String): List<Movie> {
        return this.filter { it.title.lowercase().contains(filter.lowercase()) }
    }

    fun navigate(it: Int) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(it)
        findNavController.navigate(action)
    }


}

fun MainFragment.buildState(): MainState {
    return MainState(findNavController())
}