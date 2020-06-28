package com.manuflowers.moviefinder.data.local

interface MovieFinderDataManager {
    fun isUserLoggedIn(): Boolean
    fun saveUserState(userState: Boolean)
}