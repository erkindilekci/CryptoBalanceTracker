package com.erkindilekci.domain.usecase.worker

import android.content.Context
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class WorkerRequest(context: Context) {

    private val workManager = WorkManager.getInstance(context)

    fun periodicRequest() {
        val request = PeriodicWorkRequestBuilder<NotificationWorker>(60, TimeUnit.MINUTES)
            .setInitialDelay(60, TimeUnit.SECONDS)
            .build()
        workManager.enqueue(request)
    }
}
