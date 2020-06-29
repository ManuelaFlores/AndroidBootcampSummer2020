package com.manuflowers.moviefinder.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.manuflowers.moviefinder.application.MovieFinderApplication

class HomeViewModelFactory:ViewModelProvider.Factory  {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
            return HomeViewModel(
                application = MovieFinderApplication()
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}