package com.manuflowers.photoinspiration.ui.login

import androidx.lifecycle.*
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.data.PhotoInspirationRepository
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginSuccess
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginUserNameFailure
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginUserPasswordFailure
import kotlinx.coroutines.Dispatchers

class LoginViewModel(
    private val repository: PhotoInspirationRepository
) : ViewModel() {

    /**
     * A method to emit the LoginFormState
     * @param userName the current value of the user name edit text
     * @param password the current value of the user password edit tex
     * */

    fun isValidForm(userName: String, password: String) =
        liveData(viewModelScope.coroutineContext + Dispatchers.IO) {
            when {
                !isValidUserName(userName) -> {
                    emit(LoginUserNameFailure(error = R.string.invalid_username))
                }
                !isPasswordValid(password) -> {
                    emit(LoginUserPasswordFailure(error = R.string.invalid_password))
                }
                else -> {
                    emit(LoginSuccess(isDataValid = true))
                }
            }
        }

    /**
     * A method to set the current state of the user
     * @param userState the current user state
     * */
    fun saveUserState(userState: Boolean) = repository.saveUserState(userState)

    fun isUserLoggedIn() = repository.isUserLoggedIn

    fun isValidUserName(userName: String) = userName.trim().isNotEmpty()

    fun isPasswordValid(password: String) =
        password.trim().isNotEmpty() && password.trim().length >= 4
}