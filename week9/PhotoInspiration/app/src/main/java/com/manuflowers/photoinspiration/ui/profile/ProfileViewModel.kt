package com.manuflowers.photoinspiration.ui.profile

import androidx.lifecycle.ViewModel
import com.manuflowers.photoinspiration.data.PhotoInspirationRepository

class ProfileViewModel (
    private val repository: PhotoInspirationRepository
): ViewModel() {

    fun saveUserState(userState: Boolean) = repository.saveUserState(userState)

}