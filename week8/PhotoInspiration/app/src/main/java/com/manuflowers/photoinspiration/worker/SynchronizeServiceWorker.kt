package com.manuflowers.photoinspiration.worker

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.PhotosInspirationRepository
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService
import com.manuflowers.photoinspiration.util.showNotification

const val NOTIFICATION_CHANNEL_NAME = "Synchronize service channel"
const val NOTIFICATION_CHANNEL_ID = "Synchronize ID"

class SynchronizeServiceWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {

    private val apiService by lazy { buildApiService() }

    private val remoteApi by lazy { RemoteApiManager(apiService) }

    private val photosDao: PhotosDao =
        PhotoInspirationDatabase.getDataBase(PhotoInspirationApplication.getAppContext())
            .photosDao()

    private val repository by lazy {
        PhotosInspirationRepository(
            remoteApiManager = remoteApi,
            photosDao = photosDao,
            preferences = PhotoInspirationPreferences()
        )
    }

    override suspend fun doWork(): Result {
        return try {
            repository.fetchAndSavePhotos(1, 20)
            showNotification(applicationContext)
            Result.success()
        } catch (error: Throwable) {
            error.printStackTrace()
            Result.failure()
        }
    }

}