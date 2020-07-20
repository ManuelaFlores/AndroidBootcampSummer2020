package com.manuflowers.photoinspiration.data

import androidx.lifecycle.LiveData
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.models.Success
import com.manuflowers.photoinspiration.data.models.asPhotoEntity
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PhotosInspirationRepository(
    private val remoteApiManager: RemoteApiManager,
    private val photosDao: PhotosDao,
    private val preferences: PhotoInspirationPreferences
) {

    val isUserLoggedIn: Boolean
        get() = preferences.userState

    fun saveUserState(userState: Boolean) {
        preferences.saveUserState(userState)
    }

    suspend fun fetchAndSavePhotos(page: Int, pageSize: Int) {
        withContext(Dispatchers.IO) {
            val allRemotePhotosResult = remoteApiManager.getPhotos(page, pageSize)
            if (allRemotePhotosResult is Success) {
                val allEntitiesPhotos = allRemotePhotosResult.data.map { it.asPhotoEntity() }
                clearAllPhotosFromDataBase()
                photosDao.insertAllPhotos(allEntitiesPhotos)
            }
        }
    }

    fun getAllPhotosFromDatabase(): LiveData<MutableList<PhotoEntity>> = photosDao.getAllPhotos()

    private suspend fun clearAllPhotosFromDataBase() = withContext(Dispatchers.IO) {
        photosDao.deleteAllPhotos()
    }

}