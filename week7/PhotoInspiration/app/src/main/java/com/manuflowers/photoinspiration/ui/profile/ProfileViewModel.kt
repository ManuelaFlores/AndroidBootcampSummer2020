package com.manuflowers.photoinspiration.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.PhotosInspirationRepository
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService

class ProfileViewModel : ViewModel() {
    private val apiService by lazy { buildApiService() }

    private val remoteApi by lazy { RemoteApiManager(apiService) }

    private val repository: PhotosInspirationRepository

    init {
        val photosDao: PhotosDao =
            PhotoInspirationDatabase.getDataBase(PhotoInspirationApplication.getAppContext())
                .photosDao()

        repository = PhotosInspirationRepository(
            remoteApi,
            photosDao,
            PhotoInspirationPreferences()
        )
    }

    fun saveUserState(userState: Boolean) = repository.saveUserState(userState)

}