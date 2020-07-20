package com.manuflowers.photoinspiration.worker

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Context.NOTIFICATION_SERVICE
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.PhotosInspirationRepository
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService

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
            repository.fetchAndSavePhotos()
            showNotification()
            Result.success()
        } catch (error: Throwable) {
            error.printStackTrace()
            Result.failure()
        }
    }

    private fun showNotification() {
        val notificationManager = (applicationContext.getSystemService(NOTIFICATION_SERVICE)) as NotificationManager

        createNotificationChannel(notificationManager)

        val builder = NotificationCompat.Builder(applicationContext, NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_app)
            .setContentTitle("Synchronization service")
            .setContentText("Synchronizing home")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        notificationManager.notify(1, builder)

    }

    private fun createNotificationChannel(manager: NotificationManager?) {
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
            val serviceChannel = NotificationChannel(
                    NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
            )
            manager?.createNotificationChannel(serviceChannel)
        }
    }

}