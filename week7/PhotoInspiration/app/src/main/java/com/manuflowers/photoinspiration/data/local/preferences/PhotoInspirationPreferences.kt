package com.manuflowers.photoinspiration.data.local.preferences

import android.content.Context
import android.content.SharedPreferences
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication

class PhotoInspirationPreferences {

    private fun getDefaultSharedPreferences(): SharedPreferences =
        PhotoInspirationApplication.getAppContext()
            .getSharedPreferences(PHOTO_INSPIRATION_PREFERENCES, Context.MODE_PRIVATE)

    fun saveUserState(userState: Boolean) {
        val editor = getDefaultSharedPreferences().edit()
        editor.putBoolean(KEY_USER_STATE, userState).apply()
    }

    val userState: Boolean
        get() = getDefaultSharedPreferences().getBoolean(KEY_USER_STATE, false)

    companion object {
        private const val PHOTO_INSPIRATION_PREFERENCES = "PHOTO_INSPIRATION_PREFERENCES"
        private const val KEY_USER_STATE = "KEY_USER_STATE"
    }

}