package com.manuflowers.photoinspiration.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.data.PhotoInspirationRepository
import com.manuflowers.photoinspiration.util.showNotification

const val NOTIFICATION_CHANNEL_NAME = "Synchronize service channel"
const val NOTIFICATION_CHANNEL_ID = "Synchronize ID"

class SynchronizeServiceWorker(
    context: Context,
    workerParameters: WorkerParameters,
    private val repository: PhotoInspirationRepository
) :
    CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        return try {
            repository.fetchPhotos(1, 20)
            showNotification(
                context = applicationContext,
                title = applicationContext.getString(R.string.synchronization_service),
                description = applicationContext.getString(
                    R.string.synchronizing_home
                )
            )
            Result.success()
        } catch (error: Throwable) {
            error.printStackTrace()
            Result.failure()
        }
    }
}