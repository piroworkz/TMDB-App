package com.piroworkz.bluelabstmdbapp.app.ui.splash

import androidx.constraintlayout.motion.widget.MotionLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.piroworkz.bluelabstmdbapp.R

class SplashState(private var navController: NavController) {

    val transitionListener: MotionLayout.TransitionListener =
        object : MotionLayout.TransitionListener {
            override fun onTransitionStarted(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int
            ) {

            }

            override fun onTransitionChange(
                motionLayout: MotionLayout?,
                startId: Int,
                endId: Int,
                progress: Float
            ) {

            }

            override fun onTransitionCompleted(motionLayout: MotionLayout?, currentId: Int) {
                navController.apply {
                    popBackStack(R.id.splashFragment, true)
                    navigate(R.id.mainFragment)
                }
            }

            override fun onTransitionTrigger(
                motionLayout: MotionLayout?,
                triggerId: Int,
                positive: Boolean,
                progress: Float
            ) {

            }
        }
}


fun SplashFragment.buildState(): SplashState {
    return SplashState(findNavController())
}