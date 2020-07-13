package com.manuflowers.photoinspiration.data.remote.networking

import android.util.Log
import com.manuflowers.photoinspiration.data.models.Failure
import com.manuflowers.photoinspiration.data.models.Success

const val BASE_URL = "https://api.unsplash.com"

class RemoteApiManager(private val remoteApiService: RemoteApiService) {

    suspend fun getPhotos() = try {
        val data = remoteApiService.getPhotos("xMgrwRJDt_GLYKsvxJM2b1TUAYenCzlLt0nErWCnc24", 6)
        Log.e("RESPONSE", "$data" )
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

}