package com.manuflowers.photoinspiration.data.remote.networking

import com.manuflowers.photoinspiration.data.models.Failure
import com.manuflowers.photoinspiration.data.models.Success

const val CLIENT_ID = "xMgrwRJDt_GLYKsvxJM2b1TUAYenCzlLt0nErWCnc24"

class RemoteApiManager(private val remoteApiService: RemoteApiService) {

    suspend fun getPhotos(page: Int, pageSize: Int) = try {
        val data = remoteApiService.getPhotos(CLIENT_ID, page, pageSize)
        Success(data)
    } catch (error: Throwable) {
        Failure(error)
    }

}