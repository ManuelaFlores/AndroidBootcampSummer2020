package com.manuflowers.photoinspiration.application

import android.app.Application
import android.content.Context

class PhotoInspirationApplication : Application() {
    companion object {
        private lateinit var instance: PhotoInspirationApplication
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }
}