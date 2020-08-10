package com.manuflowers.photoinspiration.di

import com.manuflowers.photoinspiration.data.PhotoInspirationRepository
import com.manuflowers.photoinspiration.data.PhotosInspirationRepositoryImpl
import org.koin.dsl.module

val repositoryModule = module {

    single<PhotoInspirationRepository> { PhotosInspirationRepositoryImpl(get(), get(), get()) }

}