package com.piroworkz.bluelabstmdbapp.app.ui.main

import android.content.Context
import androidx.recyclerview.widget.GridLayoutManager

class MoviesGridLayoutManager(context: Context) :
    GridLayoutManager(context, 6, HORIZONTAL, false) {
    init {
        spanSizeLookup = object : SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int = when (position % 5) {
                0, 1 -> 3
                else -> 2
            }
        }
    }
}