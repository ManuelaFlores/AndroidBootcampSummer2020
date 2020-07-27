package com.manuflowers.photoinspiration.data

import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.models.*
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

     override suspend fun fetchPhotos(page: Int, pageSize: Int): Result<Flow<MutableList<PhotoEntity>>> {
        return when (val result = remoteApiManager.getPhotos(page, pageSize)) {
            is Success -> {
                val allEntities = result.data.map { it.asPhotoEntity() }
                clearAllPhotosFromDataBase()
                photosDao.insertAllPhotos(allEntities)
                Success(getAllPhotosFromDatabase())
            }
            is Failure -> {
                Failure(result.error)
            }
        }
    }

    override fun getAllPhotosFromDatabase(): Flow<MutableList<PhotoEntity>> =
        photosDao.getAllPhotos()

    override suspend fun clearAllPhotosFromDataBase()  {
        photosDao.deleteAllPhotos()
    }

}