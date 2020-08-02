package com.manuflowers.photoinspiration.di

import com.manuflowers.photoinspiration.ui.home.HomeViewModel
import com.manuflowers.photoinspiration.ui.login.LoginViewModel
import com.manuflowers.photoinspiration.ui.profile.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val presentationModule = module {
    viewModel { HomeViewModel(get()) }
    viewModel { LoginViewModel(get()) }
    viewModel { ProfileViewModel(get()) }
}