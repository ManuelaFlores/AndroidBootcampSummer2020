package com.manuflowers.photoinspiration.data

import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager

class PhotosInspirationRepositoryImpl(
    private val remoteApiManager: RemoteApiManager
) {

     suspend fun getPhotos(page: Int) = remoteApiManager.getPhotos(page)

}