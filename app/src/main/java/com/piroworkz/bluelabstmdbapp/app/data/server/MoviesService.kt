package com.piroworkz.bluelabstmdbapp.app.data.server

import com.piroworkz.bluelabstmdbapp.app.data.server.model.RemoteNowPlaying
import com.piroworkz.bluelabstmdbapp.app.data.server.model.RemoteTopRated
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesService {

    @GET("3/movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "sp-MX"
    ): RemoteTopRated

    @GET("/3/movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") apikey: String,
        @Query("language") language: String = "sp-MX"
    ): RemoteNowPlaying
}