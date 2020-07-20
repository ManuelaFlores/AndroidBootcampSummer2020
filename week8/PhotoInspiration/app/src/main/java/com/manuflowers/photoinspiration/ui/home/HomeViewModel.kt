package com.manuflowers.photoinspiration.ui.home

import android.app.Application
import androidx.lifecycle.*
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.PhotosInspirationRepository
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.models.ErrorNetworkMessage
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.remote.networking.NetworkStatusChecker
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService
import kotlinx.coroutines.launch

class HomeViewModel(application: Application) : AndroidViewModel(application) {

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

    private val errorNetworkMessageMutableLiveData = MutableLiveData<ErrorNetworkMessage>()
    val errorNetworkMessageLiveData: LiveData<ErrorNetworkMessage>
        get() = errorNetworkMessageMutableLiveData

    val allMovies: LiveData<MutableList<PhotoEntity>>
        get() = repository.getAllPhotosFromDatabase().asLiveData()

    fun getMovies(onSuccessSavedData: () -> Unit, page: Int = 1, pageSize: Int = 20) {
        if (NetworkStatusChecker(PhotoInspirationApplication.getAppContext()).hasInternetConnection()) {
            viewModelScope.launch {
                repository.fetchAndSavePhotos(page, pageSize)
            }
            onSuccessSavedData()
        } else {
            sendErrorNetworkMessage()
        }
    }

    private fun sendErrorNetworkMessage() {
        if (!NetworkStatusChecker(PhotoInspirationApplication.getAppContext()).hasInternetConnection()) {
            errorNetworkMessageMutableLiveData.value =
                (ErrorNetworkMessage(R.string.there_is_no_internet_connection))
        }
    }

    fun getAllPhotosToOrder(): LiveData<MutableList<PhotoEntity>> =
        repository.getAllPhotosFromDatabase().asLiveData()

}