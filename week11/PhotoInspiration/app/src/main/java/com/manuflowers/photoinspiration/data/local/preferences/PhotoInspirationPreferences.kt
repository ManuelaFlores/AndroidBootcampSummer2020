package com.manuflowers.photoinspiration.data.local.preferences

import android.content.SharedPreferences

class PhotoInspirationPreferences(
    private val preferences: SharedPreferences
) {

    fun saveUserState(userState: Boolean) {
        val editor = preferences.edit()
        editor.putBoolean(KEY_USER_STATE, userState).apply()
    }

    val userState: Boolean
        get() = preferences.getBoolean(KEY_USER_STATE, false)

    companion object {
        private const val KEY_USER_STATE = "KEY_USER_STATE"
    }

}