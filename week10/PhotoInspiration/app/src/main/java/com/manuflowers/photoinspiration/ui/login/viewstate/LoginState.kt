package com.manuflowers.photoinspiration.ui.login.viewstate

/**
 * LoginState, general state for success and failure state
 * */
sealed class LoginState

class LoginSuccess(val isDataValid: Boolean) : LoginState()

sealed class LoginFailure : LoginState()
class LoginUserNameFailure(val error: Int) : LoginFailure()
class LoginUserPasswordFailure(val error: Int) : LoginFailure()