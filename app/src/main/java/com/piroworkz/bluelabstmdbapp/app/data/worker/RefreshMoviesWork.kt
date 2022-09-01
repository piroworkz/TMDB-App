package com.piroworkz.bluelabstmdbapp.app.data.worker

import android.content.Context
import androidx.work.*
import java.util.concurrent.TimeUnit


class RefreshMoviesWork(context: Context) {
    private val constraints = Constraints.Builder()
        .setRequiredNetworkType(NetworkType.UNMETERED)
        .setRequiresCharging(true)
        .setRequiresBatteryNotLow(true)
        .build()

    private val repeatingRequest =
        PeriodicWorkRequestBuilder<RefreshMoviesWorker>(1, TimeUnit.DAYS)
            .setConstraints(constraints)
            .build()
    private val instance = WorkManager.getInstance(context)

    fun doWork() {
        instance.enqueueUniquePeriodicWork(
            RefreshMoviesWorker.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            repeatingRequest
        )
    }
}