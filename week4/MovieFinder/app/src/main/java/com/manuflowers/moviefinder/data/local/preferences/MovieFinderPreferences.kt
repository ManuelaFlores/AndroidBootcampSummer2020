package com.manuflowers.moviefinder.data.local.preferences

import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.manuflowers.moviefinder.application.MovieFinderApplication

object MovieFinderPreferences {
    private const val KEY_USER_STATE = "KEY_USER_STATE"

    private fun getDefaultSharedPreferences(): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(MovieFinderApplication.getAppContext())

    fun saveUserState(userState: Boolean) {
        val editor = getDefaultSharedPreferences().edit()
        editor.putBoolean(KEY_USER_STATE, userState).apply()
    }

    val userState: Boolean
        get() = getDefaultSharedPreferences().getBoolean(KEY_USER_STATE, false)

}