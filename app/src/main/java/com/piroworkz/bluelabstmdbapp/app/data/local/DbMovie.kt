package com.piroworkz.bluelabstmdbapp.app.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.piroworkz.bluelabstmdbapp.domain.MovieType

@Entity(tableName = "movies")
data class DbMovie(
    @PrimaryKey(autoGenerate = false)
    val movieId: Int,
    val posterPath: String,
    val title: String,
    val voteAverage: String,
    val overview: String,
    val backDropPath: String,
    val type: MovieType
)