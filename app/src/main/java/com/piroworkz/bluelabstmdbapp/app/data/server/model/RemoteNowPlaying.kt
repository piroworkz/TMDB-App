package com.piroworkz.bluelabstmdbapp.app.data.server.model

data class RemoteNowPlaying(
    val dates: Dates,
    val page: Int,
    val results: List<Result>,
    val total_pages: Int,
    val total_results: Int
)