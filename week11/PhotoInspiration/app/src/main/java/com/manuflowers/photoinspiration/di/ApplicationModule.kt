package com.manuflowers.photoinspiration.di

import android.content.Context
import androidx.work.WorkManager
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.util.WorkManagerHelper
import com.manuflowers.photoinspiration.worker.SynchronizeServiceWorker
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val applicationModule = module {
    single {
        androidContext().getSharedPreferences(
            PHOTO_INSPIRATION_PREFERENCES,
            Context.MODE_PRIVATE
        )
    }

    single {
        PhotoInspirationPreferences(get())
    }

    single {
        WorkManager.getInstance(get())
    }

    single { WorkManagerHelper(get()) }

    single { SynchronizeServiceWorker(androidContext(), get(), get()) }
}

private const val PHOTO_INSPIRATION_PREFERENCES = "PHOTO_INSPIRATION_PREFERENCES"