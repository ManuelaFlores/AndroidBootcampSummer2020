package com.manuflowers.moviefinder.application

import android.app.Application
import android.content.Context

class MovieFinderApplication : Application() {

    companion object {
        private lateinit var instance: MovieFinderApplication
        fun getAppContext(): Context = instance.applicationContext
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

}