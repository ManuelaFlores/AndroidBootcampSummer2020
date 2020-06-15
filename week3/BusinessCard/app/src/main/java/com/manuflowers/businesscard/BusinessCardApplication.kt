package com.manuflowers.businesscard

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.manuflowers.businesscard.Constants.PREFERENCES_MODE
import com.manuflowers.businesscard.Constants.THEME_MODE_KEY

class BusinessCardApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        val preferences = getSharedPreferences(PREFERENCES_MODE, Context.MODE_PRIVATE)
        val nightMode = when(preferences.getInt(THEME_MODE_KEY,0)) {
            ThemeMode.LIGHT.ordinal -> AppCompatDelegate.MODE_NIGHT_NO
            ThemeMode.DARK.ordinal -> AppCompatDelegate.MODE_NIGHT_YES
            ThemeMode.SYSTEM.ordinal -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
            ThemeMode.BATTERY.ordinal -> AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY
            else -> AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM
        }
        AppCompatDelegate.setDefaultNightMode(nightMode)
    }
}