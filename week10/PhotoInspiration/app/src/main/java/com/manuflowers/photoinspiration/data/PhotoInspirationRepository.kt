package com.manuflowers.photoinspiration.data

import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.models.Result
import kotlinx.coroutines.flow.Flow

interface PhotoInspirationRepository {
    val isUserLoggedIn: Boolean

    fun saveUserState(userState: Boolean)

    fun getAllPhotosFromDatabase(): Flow<MutableList<PhotoEntity>>

    suspend fun clearAllPhotosFromDataBase()

    suspend fun fetchPhotos(page: Int, pageSize: Int): Result<Flow<MutableList<PhotoEntity>>>
}