package com.piroworkz.bluelabstmdbapp.app.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT COUNT(movieId) FROM movies WHERE type = 0")
    suspend fun countTopRated(): Int

    @Query("SELECT COUNT(movieId) FROM movies WHERE type = 1")
    suspend fun countNowPlaying(): Int

    @Query("SELECT * FROM movies WHERE type = 0")
    fun getTopRated(): Flow<List<DbMovie>>

    @Query("SELECT * FROM movies WHERE type = 1")
    fun getNowPlaying(): Flow<List<DbMovie>>

    @Insert(onConflict = REPLACE)
    suspend fun saveTopRatedMovies(movies: List<DbMovie>)

    @Insert(onConflict = REPLACE)
    suspend fun saveNowPlayingMovies(movies: List<DbMovie>)

    @Query("SELECT * FROM movies WHERE movieId = :movieId")
    fun findMovieById(movieId: Int): Flow<DbMovie>
}