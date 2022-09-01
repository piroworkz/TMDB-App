package com.piroworkz.bluelabstmdbapp.app.ui.main

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.app.ui.common.diffUtil
import com.piroworkz.bluelabstmdbapp.app.ui.common.inflate
import com.piroworkz.bluelabstmdbapp.databinding.MovieBinding
import com.piroworkz.bluelabstmdbapp.domain.Movie

class MoviesAdapter(private val ratio: Float = 1F, private val onClickListener: (Int) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(diffUtil { old, new -> old.movieId == new.movieId }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.movie, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), ratio, onClickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding = MovieBinding.bind(view)

        fun bind(movie: Movie, ratio: Float, onClickListener: (Int) -> Unit) {
            binding.movie = movie
            binding.poster.setRatio(ratio)
            binding.rootLayout.setOnClickListener { onClickListener(movie.movieId) }
        }
    }
}



