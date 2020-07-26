package com.manuflowers.photoinspiration.data

import com.manuflowers.photoinspiration.data.models.PhotoEntity
import kotlinx.coroutines.flow.Flow

interface PhotoInspirationRepository {
    val isUserLoggedIn: Boolean

    fun saveUserState(userState: Boolean)

    suspend fun fetchAndSavePhotos(page: Int, pageSize: Int)

    fun getAllPhotosFromDatabase(): Flow<MutableList<PhotoEntity>>

    suspend fun clearAllPhotosFromDataBase()
}