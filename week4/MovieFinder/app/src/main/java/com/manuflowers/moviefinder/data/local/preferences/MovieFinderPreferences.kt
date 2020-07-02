package com.manuflowers.moviefinder.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.manuflowers.moviefinder.application.MovieFinderApplication

object MovieFinderPreferences {
    private const val MOVIE_FINDER_PREFERENCES = "MOVIE_FINDER_PREFERENCES"
    private const val KEY_USER_STATE = "KEY_USER_STATE"

    private fun getDefaultSharedPreferences(): SharedPreferences =
        MovieFinderApplication.getAppContext()
            .getSharedPreferences(MOVIE_FINDER_PREFERENCES, Context.MODE_PRIVATE)

    fun saveUserState(userState: Boolean) {
        val editor = getDefaultSharedPreferences().edit()
        editor.putBoolean(KEY_USER_STATE, userState).apply()
    }

    val userState: Boolean
        get() = getDefaultSharedPreferences().getBoolean(KEY_USER_STATE, false)

}