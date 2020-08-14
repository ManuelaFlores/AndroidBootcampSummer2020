package com.manuflowers.photoinspiration.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginState
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginSuccess
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginUserNameFailure
import com.manuflowers.photoinspiration.ui.login.viewstate.LoginUserPasswordFailure
import com.manuflowers.photoinspiration.util.afterTextChanged
import com.manuflowers.photoinspiration.util.toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels {
        LoginViewModelFactory(PhotoInspirationApplication.repository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.let {
            it.mainBottomNavigationView?.let { mainBottomNavigationView ->
                mainBottomNavigationView.visibility = View.GONE
            }
        }

        if (loginViewModel.isUserLoggedIn()) {
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupListeners()
    }

    private fun validateLoginFormState(loginFormState: LoginState) {
        when (loginFormState) {
            is LoginUserNameFailure -> {
                userNameTextInputLayout.error = getString(loginFormState.error)
            }
            is LoginUserPasswordFailure -> {
                passwordTextInputLayout.error = getString(loginFormState.error)
            }
            is LoginSuccess -> {
                loginButton.isEnabled = loginFormState.isDataValid
                userNameTextInputLayout.error = null
                passwordTextInputLayout.error = null
            }
        }
    }

    private fun setupListeners() {
        userNameEditText.afterTextChanged {
            loginViewModel.isValidForm(
                userName = userNameEditText.text.toString(),
                password = passwordEditText.text.toString()
            ).observe(viewLifecycleOwner, Observer {
                validateLoginFormState(it)
            })
        }

        passwordEditText.afterTextChanged {
            loginViewModel.isValidForm(
                userName = userNameEditText.text.toString(),
                password = passwordEditText.text.toString()
            ).observe(viewLifecycleOwner, Observer {
                validateLoginFormState(it)
            })
        }

        loginButton.setOnClickListener {
            startHomeFragment()
        }
    }

    private fun startHomeFragment() {
        loginViewModel.saveUserState(true)
        activity?.toast(getString(R.string.welcome_to_photo_inspiration))
        findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
    }
}