package com.manuflowers.photoinspiration.data.remote.networking

import com.manuflowers.photoinspiration.data.models.Failure
import com.manuflowers.photoinspiration.data.models.Success

const val BASE_URL = "https://api.unsplash.com"
const val CLIENT_ID = "xMgrwRJDt_GLYKsvxJM2b1TUAYenCzlLt0nErWCnc24"

class RemoteApiManager(private val remoteApiService: RemoteApiService) {

    suspend fun getPhotos(page: Int = 1, pageSize: Int = 20) = try {
        val data = remoteApiService.getPhotos(CLIENT_ID, page, pageSize)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

}