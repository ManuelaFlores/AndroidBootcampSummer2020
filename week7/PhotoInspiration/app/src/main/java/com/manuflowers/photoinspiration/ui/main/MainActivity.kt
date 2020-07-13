package com.manuflowers.photoinspiration.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.application.PhotoInspirationApplication
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val remoteApi = PhotoInspirationApplication.remoteApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setup()
    }

    private fun setup() {

    }
}