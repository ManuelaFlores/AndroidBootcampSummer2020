package com.manuflowers.moviefinder.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.manuflowers.moviefinder.R
import com.manuflowers.moviefinder.utils.afterTextChanged
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*

class LoginFragment : Fragment() {

    private val loginViewModel: LoginViewModel by viewModels()

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
        observeChanges()
        setupListeners()
    }

    private fun observeChanges() {
        loginViewModel.loginFormStateLiveData.observe(
            viewLifecycleOwner,
            Observer { loginFormState ->
                loginFormState.usernameError?.let {
                    userNameTextInputLayout.error = getString(it)
                }
                loginFormState.passwordError?.let {
                    passwordTextInputLayout.error = getString(it)
                }
                if (loginFormState.usernameError == null) userNameTextInputLayout.error = null
                if (loginFormState.passwordError == null) passwordTextInputLayout.error = null

                loginButton.isEnabled = loginFormState.isDataValid
            })
    }

    private fun setupListeners() {
        userNameEditText.afterTextChanged {
            loginViewModel.isValidForm(
                userName = userNameEditText.text.toString(),
                password = passwordEditText.text.toString()
            )
        }

        passwordEditText.afterTextChanged {
            loginViewModel.isValidForm(
                userName = userNameEditText.text.toString(),
                password = passwordEditText.text.toString()
            )
        }

        loginButton.setOnClickListener {
            loginViewModel.saveUserState(true)
            showToast(getString(R.string.welcome_to_movie_finder))
            findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()
    }
}