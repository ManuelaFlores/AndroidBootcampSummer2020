package com.manuflowers.moviefinder.ui.login

import androidx.lifecycle.*
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.application.MovieFinderApplication
import com.manuflowers.moviefinder.data.local.MovieFinderDataManager
import com.manuflowers.moviefinder.data.local.MovieFinderDataManagerImpl
import com.manuflowers.moviefinder.data.local.database.MovieFinderDatabase
import com.manuflowers.moviefinder.data.models.LoginFormState
import kotlinx.coroutines.Dispatchers

class LoginViewModel : ViewModel() {

    private val movieFinderDataManager: MovieFinderDataManager =
        MovieFinderDataManagerImpl(
            MovieFinderDatabase.getDataBase(
                MovieFinderApplication.getAppContext(),
                viewModelScope
            ).movieDao()
        )

    /**
     * A method to retrieve the LoginFormState
     * @param userName the current value of the user name edit text
     * @param password the current value of the user password edit tex
     * */

    fun isValidForm(userName: String, password: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            when {
                !isValidUserName(userName) -> {
                    emit(LoginFormState(usernameError = R.string.invalid_username))
                }
                !isPasswordValid(password) -> {
                    emit(LoginFormState(passwordError = R.string.invalid_password))
                }
                else -> {
                    emit(LoginFormState(isDataValid = true))
                }
            }
        }

    /**
     * A method to set the current state of the user
     * @param userState the current user state
     * */
    fun saveUserState(userState: Boolean) = movieFinderDataManager.saveUserState(userState)

    fun isUserLoggedIn() = movieFinderDataManager.isUserLoggedIn

    private fun isValidUserName(userName: String) = userName.trim().isNotEmpty()

    private fun isPasswordValid(password: String) =
        password.trim().isNotEmpty() && password.trim().length >= 4
}