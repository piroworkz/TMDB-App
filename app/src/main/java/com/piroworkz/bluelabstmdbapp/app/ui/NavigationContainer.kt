package com.piroworkz.bluelabstmdbapp.app.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.piroworkz.bluelabstmdbapp.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NavigationContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}