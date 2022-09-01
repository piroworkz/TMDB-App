package com.piroworkz.bluelabstmdbapp.app.ui.splash

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private lateinit var binding: FragmentSplashBinding
    private lateinit var stateHolder: SplashState

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSplashBinding.bind(view)
        stateHolder = buildState()

        binding.constraintLayout.setTransitionListener(stateHolder.transitionListener)
    }
}