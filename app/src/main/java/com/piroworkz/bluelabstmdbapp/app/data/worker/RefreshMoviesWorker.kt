package com.piroworkz.bluelabstmdbapp.app.data.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.piroworkz.bluelabstmdbapp.usecases.RequestNowPlayingMoviesUseCase
import com.piroworkz.bluelabstmdbapp.usecases.RequestTopRatedMoviesUseCase
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

@HiltWorker
class RefreshMoviesWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParameters: WorkerParameters,
    private val requestTopRatedMoviesUseCase: RequestTopRatedMoviesUseCase,
    private val requestNowPlayingMoviesUseCase: RequestNowPlayingMoviesUseCase
) : CoroutineWorker(context, workerParameters) {
    companion object {
        const val WORK_NAME = "RefreshMoviesWorker"
    }

    override suspend fun doWork(): Result {
        return try {
            requestNowPlayingMoviesUseCase()
            requestTopRatedMoviesUseCase()
            Result.success()
        } catch (e: Exception) {
            Result.failure()
        }
    }
}