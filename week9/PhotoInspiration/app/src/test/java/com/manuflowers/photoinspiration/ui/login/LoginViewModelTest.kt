package com.manuflowers.photoinspiration.ui.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.manuflowers.photoinspiration.data.PhotoInspirationRepository
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class LoginViewModelTest {
    private lateinit var loginViewModel: LoginViewModel

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    lateinit var repository: PhotoInspirationRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        loginViewModel = LoginViewModel(repository)
    }

    @Test
    fun testCantSaveEmptyPassword() {
        val password = ""
        val canSaveEmptyPassword = loginViewModel.isPasswordValid(password)
        assertEquals(false, canSaveEmptyPassword)
    }

    @Test
    fun testCantSaveEmptyUserName() {
        val userName = ""
        val canSaveUserName = loginViewModel.isValidUserName(userName)
        assertEquals(false, canSaveUserName)
    }


}