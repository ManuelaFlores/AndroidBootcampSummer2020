package com.manuflowers.moviefinder.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuflowers.moviefinder.data.local.MovieFinderDataManagerImpl
import com.manuflowers.moviefinder.data.local.preferences.MovieFinderPreferences

class ProfileViewModelFactory: ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                movieFinderDataManager = MovieFinderDataManagerImpl(MovieFinderPreferences)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}