package com.manuflowers.photoinspiration.application

import android.app.Application
import android.content.Context
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService

class PhotoInspirationApplication : Application() {
    companion object {
        private lateinit var instance: PhotoInspirationApplication
        fun getAppContext(): Context = instance.applicationContext

        private val apiService by lazy { buildApiService() }

        val remoteApi by lazy { RemoteApiManager(apiService) }
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}