package com.manuflowers.photoinspiration.di

import androidx.room.Room
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import org.koin.dsl.module

val databaseModule = module {

    single {
        Room.databaseBuilder(
            PhotoInspirationApplication.getAppContext(),
            PhotoInspirationDatabase::class.java,
            "photo_inspiration_database"
        ).build()
    }

    single { get<PhotoInspirationDatabase>().photosDao() }
}