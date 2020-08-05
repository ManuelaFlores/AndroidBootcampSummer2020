package com.manuflowers.photoinspiration.ui.home

import androidx.lifecycle.*
import com.manuflowers.photoinspiration.data.PhotoInspirationRepository
import com.manuflowers.photoinspiration.data.models.Failure
import com.manuflowers.photoinspiration.data.models.PhotoEntity
import com.manuflowers.photoinspiration.data.models.Success
import com.manuflowers.photoinspiration.ui.home.viewstate.HomeState
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosFailure
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosOffLine
import com.manuflowers.photoinspiration.ui.home.viewstate.PhotosSuccess
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: PhotoInspirationRepository
) : ViewModel() {

    private val currentPage = 1
    private val pageSize = 20

    private val homeStateMutableLiveData = MutableLiveData<HomeState>()
    val homeStateLiveData: LiveData<HomeState>
        get() = homeStateMutableLiveData


    fun getPhotos() {
        viewModelScope.launch(Dispatchers.IO) {
            when (val result = repository.fetchPhotos(currentPage, pageSize)) {
                is Success -> {
                    result.data.collect {
                        homeStateMutableLiveData.postValue(PhotosSuccess(it))
                    }
                }
                is Failure -> {
                    homeStateMutableLiveData.postValue(
                        PhotosFailure(
                            result.error?.localizedMessage ?: ""
                        )
                    )
                }
            }
        }
    }

    fun getPhotosFromDb() {
        viewModelScope.launch {
            repository.getAllPhotosFromDatabase().collect {
                homeStateMutableLiveData.postValue(PhotosOffLine(it))
            }
        }
    }

    fun getAllPhotosToOrder(): LiveData<MutableList<PhotoEntity>> =
        repository.getAllPhotosFromDatabase().asLiveData()

}