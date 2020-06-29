package com.manuflowers.moviefinder.ui.profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.manuflowers.moviefinder.application.MovieFinderApplication
import com.manuflowers.moviefinder.data.local.MovieFinderDataManager
import com.manuflowers.moviefinder.data.local.MovieFinderDataManagerImpl
import com.manuflowers.moviefinder.data.local.database.MovieFinderDatabase

class ProfileViewModel : ViewModel() {

    private val movieFinderDataManager: MovieFinderDataManager =
        MovieFinderDataManagerImpl(
            MovieFinderDatabase.getDataBase(
                MovieFinderApplication.getAppContext(),
                viewModelScope
            ).movieDao()
        )

    fun saveUserState(userState: Boolean) = movieFinderDataManager.saveUserState(userState)

}