package com.manuflowers.photoinspiration.ui.main

import androidx.lifecycle.ViewModel
import com.manuflowers.photoinspiration.util.WorkManagerHelper

class MainViewModel(
    private val workManagerHelper: WorkManagerHelper
): ViewModel() {

    fun setUpSynchronization() {
        workManagerHelper.setUpSynchronization()
    }

    fun closeSynchronization() {
        workManagerHelper.closeSynchronization()
    }
}