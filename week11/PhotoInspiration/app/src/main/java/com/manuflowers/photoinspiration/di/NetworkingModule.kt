package com.manuflowers.photoinspiration.di

import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val networkingModule = module {
    single(named("BASE_URL")) { "https://api.unsplash.com" }

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }).build()
    }

    single {
        Retrofit.Builder()
            .client(get())
            .baseUrl(get<String>(named("BASE_URL")))
            .addConverterFactory(MoshiConverterFactory.create().asLenient())
            .build()
    }

    single { get<Retrofit>().create(RemoteApiService::class.java) }

    single { RemoteApiManager(get()) }
}