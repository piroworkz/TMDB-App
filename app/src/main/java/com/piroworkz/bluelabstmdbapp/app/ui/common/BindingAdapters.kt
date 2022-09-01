package com.piroworkz.bluelabstmdbapp.app.ui.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.piroworkz.bluelabstmdbapp.R
import com.piroworkz.bluelabstmdbapp.app.ui.main.MoviesAdapter
import com.piroworkz.bluelabstmdbapp.domain.Movie

@BindingAdapter("loadImg")
fun ImageView.loadImg(url: String?) {
    url?.let {
        Glide.with(context)
            .load(it)
            .placeholder(R.drawable.img)
            .into(this)
    }
}

@BindingAdapter("submitList")
fun RecyclerView.submitList(movies: List<Movie>?) {
    movies?.let {
        (adapter as MoviesAdapter).submitList(movies)
    }
}

@BindingAdapter("setMovie")
fun MovieDetailInfoView.setMovie(movie: Movie?) {
    movie?.let(this::setMovie)
}