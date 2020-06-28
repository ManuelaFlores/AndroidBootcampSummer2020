package com.manuflowers.moviefinder.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.data.local.MovieFinderDataManager

class LoginViewModel(private val movieFinderDataManager: MovieFinderDataManager): ViewModel() {

    private val loginFormStateMutableLiveData = MutableLiveData<LoginFormState>()
    val loginFormStateLiveData : LiveData<LoginFormState>
    get() = loginFormStateMutableLiveData

    fun isValidForm(userName: String, password: String) {
        return when{
            !isValidUserName(userName) -> {
                loginFormStateMutableLiveData.value = LoginFormState(usernameError = R.string.invalid_username)
            }
            !isPasswordValid(password) ->{
                loginFormStateMutableLiveData.value = LoginFormState(passwordError =R.string.invalid_password)
            }
            else -> {
                loginFormStateMutableLiveData.value = LoginFormState(isDataValid = true)
            }
        }
    }

    fun saveUserState(userState: Boolean) = movieFinderDataManager.saveUserState(userState)

    fun isUserLoggedIn() = movieFinderDataManager.isUserLoggedIn()

    private fun isValidUserName(userName: String) = userName.trim().isNotEmpty()

    private fun isPasswordValid(password: String) =
        password.trim().isNotEmpty() && password.trim().length >= 4
}

data class LoginFormState(val usernameError: Int? = null,
                          val passwordError: Int? = null,
                          val isDataValid: Boolean = false)