package com.manuflowers.moviefinder.data.local

import com.manuflowers.moviefinder.data.local.preferences.MovieFinderPreferences

class MovieFinderDataManagerImpl(private val preferences: MovieFinderPreferences) : MovieFinderDataManager {

    override fun saveUserState(userState: Boolean) {
        preferences.saveUserState(userState)
    }

    override fun isUserLoggedIn(): Boolean = preferences.userState
}