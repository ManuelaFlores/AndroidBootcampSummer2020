package com.manuflowers.photoinspiration.util

import androidx.work.*
import com.manuflowers.photoinspiration.worker.SynchronizeServiceWorker
import java.util.concurrent.TimeUnit

open class WorkManagerHelper(
    private val workManager: WorkManager
) {

    fun setUpSynchronization() {
        val constraints = buildConstraints()
        val periodicWorker = buildWorker(constraints)
        workManager.enqueueUniquePeriodicWork(
            SynchronizeServiceWorker::class.java.name,
            ExistingPeriodicWorkPolicy.KEEP,
            periodicWorker
        )
    }

    private fun buildConstraints(): Constraints {
        return Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()
    }

    private fun buildWorker(constraints: Constraints): PeriodicWorkRequest {
        return PeriodicWorkRequestBuilder<SynchronizeServiceWorker>(1, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()
    }

    fun closeSynchronization() {
        workManager.cancelUniqueWork(
            SynchronizeServiceWorker::class.java.name
        )
    }
}