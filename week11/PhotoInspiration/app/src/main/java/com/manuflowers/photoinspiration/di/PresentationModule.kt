package com.manuflowers.photoinspiration.di

import com.manuflowers.photoinspiration.ui.home.HomeViewModel
import com.manuflowers.photoinspiration.ui.login.LoginFragment
import com.manuflowers.photoinspiration.ui.login.LoginViewModel
import com.manuflowers.photoinspiration.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { ProfileViewModel(get()) }

    //Give a name to the scope
    scope(named("LOGIN_SCOPE")) {
        //Bound the scope to the fragment
        scoped { LoginFragment() }
        //create the instance
        viewModel { LoginViewModel(get()) }

    }
}