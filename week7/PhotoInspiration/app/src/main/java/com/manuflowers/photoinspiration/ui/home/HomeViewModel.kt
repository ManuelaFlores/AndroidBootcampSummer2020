package com.manuflowers.photoinspiration.ui.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.PhotosInspirationRepositoryImpl
import com.manuflowers.photoinspiration.data.models.PhotoResponse
import com.manuflowers.photoinspiration.data.models.Success
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel(photoInspirationApplication: PhotoInspirationApplication): ViewModel() {

    private val repository: PhotosInspirationRepositoryImpl = PhotosInspirationRepositoryImpl(PhotoInspirationApplication.remoteApi)

    private val photosListMutableLiveData = MutableLiveData<List<PhotoResponse>>()
    val photosListLiveData: LiveData<List<PhotoResponse>>
    get() = photosListMutableLiveData

    fun getMovies()  {
        viewModelScope.launch(Dispatchers.Main) {
            val result = repository.getPhotos(1)
            if (result is Success) {
                photosListMutableLiveData.value = result.data
            } else {
                Log.e("errorResponse", "$result")
            }
        }
    }

}