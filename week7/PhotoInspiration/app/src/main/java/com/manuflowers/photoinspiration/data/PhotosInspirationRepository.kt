package com.manuflowers.photoinspiration.data

interface PhotosInspirationRepository {
    suspend fun getPhotos(page: Int)
}