package com.manuflowers.photoinspiration.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication

class HomeViewModelFactory:ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                application = PhotoInspirationApplication()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}