package com.piroworkz.bluelabstmdbapp.app.data.local

import androidx.room.TypeConverter
import com.piroworkz.bluelabstmdbapp.domain.MovieType

class Converters {
    @TypeConverter
    fun fromMovieType(value: MovieType): Int =
        value.ordinal

    @TypeConverter
    fun toMovieType(value: Int): MovieType =
        if (value == 0) {
            MovieType.TOP
        } else {
            MovieType.PLAYING
        }
}