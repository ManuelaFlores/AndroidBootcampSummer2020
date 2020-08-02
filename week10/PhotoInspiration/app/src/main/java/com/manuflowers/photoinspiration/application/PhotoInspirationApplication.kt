package com.manuflowers.photoinspiration.application

import android.app.Application
import com.manuflowers.photoinspiration.di.networkModule
import com.manuflowers.photoinspiration.di.presentationModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PhotoInspirationApplication : Application(){

    companion object {
        private lateinit var instance: PhotoInspirationApplication

        fun getAppContext() = instance
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        startKoin {
            androidLogger()
            androidContext(this@PhotoInspirationApplication)
            modules(listOf(networkModule, presentationModule))
        }
    }
}