package com.manuflowers.photoinspiration.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.data.PhotosInspirationRepository
import com.manuflowers.photoinspiration.data.local.database.PhotoInspirationDatabase
import com.manuflowers.photoinspiration.data.local.database.PhotosDao
import com.manuflowers.photoinspiration.data.local.preferences.PhotoInspirationPreferences
import com.manuflowers.photoinspiration.data.models.LoginFormState
import com.manuflowers.photoinspiration.data.remote.networking.RemoteApiManager
import com.manuflowers.photoinspiration.data.remote.networking.buildApiService

class LoginViewModel : ViewModel() {

    private val apiService by lazy { buildApiService() }

    private val remoteApi by lazy { RemoteApiManager(apiService) }

    private val repository: PhotosInspirationRepository

    init {
        val photosDao: PhotosDao =
            PhotoInspirationDatabase.getDataBase(PhotoInspirationApplication.getAppContext())
                .photosDao()

        repository = PhotosInspirationRepository(
            remoteApi,
            photosDao,
            PhotoInspirationPreferences()
        )
    }

    private val loginFormStateMutableLiveData = MutableLiveData<LoginFormState>()
    val loginFormStateLiveData: LiveData<LoginFormState>
        get() = loginFormStateMutableLiveData

    /**
     * A method to retrieve the LoginFormState
     * @param userName the current value of the user name edit text
     * @param password the current value of the user password edit tex
     * */
    fun isValidForm(userName: String, password: String) {
        return when {
            !isValidUserName(userName) -> {
                loginFormStateMutableLiveData.value =
                    LoginFormState(usernameError = R.string.invalid_username)
            }
            !isPasswordValid(password) -> {
                loginFormStateMutableLiveData.value =
                    LoginFormState(passwordError = R.string.invalid_password)
            }
            else -> {
                loginFormStateMutableLiveData.value = LoginFormState(isDataValid = true)
            }
        }
    }

    /**
     * A method to set the current state of the user
     * @param userState the current user state
     * */
    fun saveUserState(userState: Boolean) = repository.saveUserState(userState)

    fun isUserLoggedIn() = repository.isUserLoggedIn

    private fun isValidUserName(userName: String) = userName.trim().isNotEmpty()

    private fun isPasswordValid(password: String) =
        password.trim().isNotEmpty() && password.trim().length >= 4
}