package com.manuflowers.moviefinder.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuflowers.moviefinder.data.local.MovieFinderDataManagerImpl
import com.manuflowers.moviefinder.data.local.preferences.MovieFinderPreferences

class LoginViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                movieFinderDataManager = MovieFinderDataManagerImpl(MovieFinderPreferences)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}