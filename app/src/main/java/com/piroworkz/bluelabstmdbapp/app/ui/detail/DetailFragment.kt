package com.piroworkz.bluelabstmdbapp.app.ui.detail

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.app.ui.common.collectFlow
import com.piroworkz.bluelabstmdbapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {
    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentDetailBinding.bind(view)

        binding.movieDetailToolbar.setupWithNavController(findNavController())

        viewLifecycleOwner.collectFlow(viewModel.state) { binding.movie = it.movie }

    }
}