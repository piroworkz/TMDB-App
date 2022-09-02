package com.piroworkz.bluelabstmdbapp.app.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.app.ui.common.collectFlow
import com.piroworkz.bluelabstmdbapp.app.ui.common.dismissKeyboard
import com.piroworkz.bluelabstmdbapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val moviesAdapter = MoviesAdapter { stateHolder.navigate(it) }
    private val nowPlayingAdapter = MoviesAdapter { stateHolder.navigate(it) }

    private lateinit var binding: FragmentMainBinding
    private lateinit var stateHolder: MainState

    private val viewModel: MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        stateHolder = buildState()
        binding = FragmentMainBinding.bind(view)

        setUpTopRatedRecyclerView()
        setupNowPlayingRecyclerView()

        viewLifecycleOwner.collectFlow(viewModel.state, body = this::setUpDataBindingVariables)
    }

    private fun setupNowPlayingRecyclerView() {
        binding.nowPlayingRV.adapter = nowPlayingAdapter
    }

    private fun setUpTopRatedRecyclerView() {
        binding.topMoviesRV.adapter = moviesAdapter
    }

    private fun setUpDataBindingVariables(it: MainViewModel.MainState) {
        binding.topRated = it.topRatedMovies
        binding.nowPlaying = it.nowPlayingMovies
        search(it)
    }


    private fun search(state: MainViewModel.MainState) {
        val searchView = binding.materialToolbar.menu.findItem(R.id.searchTopRatedMovies)
            .actionView as SearchView

        searchView.setOnQueryTextListener(
            stateHolder.queryListener(
                st = state,
                nowPlayingMovie = {
                    nowPlayingAdapter.submitList(it)
                },
                topRatedMovie = {
                    moviesAdapter.submitList(it)
                },
                submit = {
                    dismissKeyboard(searchView)
                })
        )
    }

}