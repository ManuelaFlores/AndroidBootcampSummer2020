package com.manuflowers.moviefinder.data.models

/**
 * A class to save te current state of the login form
 * */
data class LoginFormState(
    val usernameError: Int? = null,
    val passwordError: Int? = null,
    val isDataValid: Boolean = false
)