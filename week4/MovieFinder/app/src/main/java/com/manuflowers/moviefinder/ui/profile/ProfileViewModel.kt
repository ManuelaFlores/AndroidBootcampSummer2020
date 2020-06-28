package com.manuflowers.moviefinder.ui.profile

import androidx.lifecycle.ViewModel
import com.manuflowers.moviefinder.data.local.MovieFinderDataManager

class ProfileViewModel(private val movieFinderDataManager: MovieFinderDataManager): ViewModel() {

    fun saveUserState(userState: Boolean) = movieFinderDataManager.saveUserState(userState)

}