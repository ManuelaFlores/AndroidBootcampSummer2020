package com.manuflowers.photoinspiration.data

import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.models.Success
import com.manuflowers.photoinspiration.data.models.asPhotoEntity
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import kotlinx.coroutines.flow.Flow

class PhotosInspirationRepositoryImpl(
    private val remoteApiManager: RemoteApiManager,
    private val photosDao: PhotosDao,
    private val preferences: PhotoInspirationPreferences
) : PhotoInspirationRepository {

    override val isUserLoggedIn: Boolean
        get() = preferences.userState

    override fun saveUserState(userState: Boolean) {
        preferences.saveUserState(userState)
    }

    override suspend fun fetchAndSavePhotos(page: Int, pageSize: Int) {
        val allRemotePhotosResult = remoteApiManager.getPhotos(page, pageSize)
        if (allRemotePhotosResult is Success) {
            val allEntitiesPhotos = allRemotePhotosResult.data.map { it.asPhotoEntity() }
            clearAllPhotosFromDataBase()
            photosDao.insertAllPhotos(allEntitiesPhotos)
        }
    }

    override fun getAllPhotosFromDatabase(): Flow<MutableList<PhotoEntity>> =
        photosDao.getAllPhotos()

    override suspend fun clearAllPhotosFromDataBase()  {
        photosDao.deleteAllPhotos()
    }

}