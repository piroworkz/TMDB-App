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

class MoviesAdapter(private val onClickListener: (Int) -> Unit) :
    ListAdapter<Movie, MoviesAdapter.ViewHolder>(diffUtil { old, new -> old.movieId == new.movieId }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(parent.inflate(R.layout.movie, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position), onClickListener)
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var binding = MovieBinding.bind(view)

        fun bind(movie: Movie, onClickListener: (Int) -> Unit) {
            binding.movie = movie
            binding.poster.setRatio(1.7F)
            binding.rootLayout.setOnClickListener { onClickListener(movie.movieId) }
        }
    }
}



