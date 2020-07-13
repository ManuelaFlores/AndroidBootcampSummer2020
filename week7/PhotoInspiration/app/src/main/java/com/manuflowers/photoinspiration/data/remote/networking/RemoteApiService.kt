package com.manuflowers.photoinspiration.data.remote.networking

import com.manuflowers.photoinspiration.data.models.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteApiService {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String,
        @Query("page") page: Int,
        @Query("per_page") pageSize: Int
    ): List<PhotoResponse>
}