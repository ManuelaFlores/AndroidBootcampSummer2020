package com.manuflowers.photoinspiration.application

import android.app.Application
import androidx.room.Room
import com.manuflowers.photoinspiration.data.PhotosInspirationRepositoryImpl
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService

class PhotoInspirationApplication : Application() {
    companion object {
        private lateinit var instance: PhotoInspirationApplication

        private val photosDatabase: PhotoInspirationDatabase by lazy {
            Room.databaseBuilder(instance, PhotoInspirationDatabase::class.java, "photo_inspiration_database")
                .build()
        }

        private val photosDao by lazy {
            photosDatabase.photosDao()
        }
        private val apiService by lazy { buildApiService() }
        private val remoteApi by lazy { RemoteApiManager(apiService) }

        val repository by lazy {
            PhotosInspirationRepositoryImpl(
                remoteApi,
                photosDao,
                PhotoInspirationPreferences()
            )
        }

        fun getAppContext() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}