package com.manuflowers.moviefinder.data.local.preferences

import android.content.Context
import com.manuflowers.moviefinder.application.MovieFinderApplication

class MovieFinderPreferences {

    private val preferences by lazy {
        MovieFinderApplication.getAppContext()
            .getSharedPreferences(MOVIE_FINDER_PREFERENCES, Context.MODE_PRIVATE)
    }

    fun saveUserState(userState: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(KEY_USER_STATE, userState).apply()
    }

    val userState: Boolean
        get() = preferences.getBoolean(KEY_USER_STATE, false)

    companion object {
        private const val MOVIE_FINDER_PREFERENCES = "MOVIE_FINDER_PREFERENCES"
        private const val KEY_USER_STATE = "KEY_USER_STATE"
    }

}