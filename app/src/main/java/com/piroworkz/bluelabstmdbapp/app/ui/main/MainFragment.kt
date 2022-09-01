package com.piroworkz.bluelabstmdbapp.app.ui.main

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.app.ui.common.collectFlow
import com.piroworkz.bluelabstmdbapp.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment(R.layout.fragment_main) {

    private val moviesAdapter = MoviesAdapter(ratio = 1.7F) {stateHolder.navigate(it)}
    private val nowPlayingAdapter = MoviesAdapter(ratio = 1.1F) {stateHolder.navigate(it)}

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
        binding.nowPlayingRV.run {
            adapter = nowPlayingAdapter
        }
    }

    private fun setUpTopRatedRecyclerView() {
        val moviesGridLayoutManager = MoviesGridLayoutManager(requireContext())
        binding.topMoviesRV.run {
            adapter = moviesAdapter
            layoutManager = moviesGridLayoutManager
        }
    }

    private fun setUpDataBindingVariables(it: MainViewModel.MainState) {
        binding.topRated = it.topRatedMovies
        binding.nowPlaying = it.nowPlayingMovies
        search(it)
    }


    private fun search(state: MainViewModel.MainState) {
        val searchView = binding.materialToolbar.menu.findItem(R.id.searchByTitle)
            .actionView as SearchView

        searchView.setOnQueryTextListener(
            stateHolder.queryListener(
                st = state,
                topRatedMovie = {
                    moviesAdapter.submitList(it)
                },
                nowPlayingMovie = {
                    nowPlayingAdapter.submitList(it)
                })
        )
    }

}