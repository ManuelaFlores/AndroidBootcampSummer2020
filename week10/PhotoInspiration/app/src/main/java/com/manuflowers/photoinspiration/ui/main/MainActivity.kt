package com.manuflowers.photoinspiration.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.work.*
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.worker.SynchronizeServiceWorker
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val host: NavHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment? ?: return

        val navController = host.navController
        NavigationUI.setupWithNavController(mainBottomNavigationView, navController)
        setupWorker()
    }

    private fun setupWorker() {
        val constraint = Constraints.Builder()
            .setRequiresBatteryNotLow(true)
            .setRequiresStorageNotLow(true)
            .setRequiredNetworkType(NetworkType.NOT_ROAMING)
            .build()

        val synchronizeServiceWorker =
            PeriodicWorkRequestBuilder<SynchronizeServiceWorker>(1, TimeUnit.HOURS)
                .setConstraints(constraint)
                .build()

        val workManager = WorkManager.getInstance(this)
        workManager.enqueueUniquePeriodicWork(
            SynchronizeServiceWorker::class.java.name,
            ExistingPeriodicWorkPolicy.KEEP,
            synchronizeServiceWorker
        )
    }

    fun closeSynchronization() {
        val workManager = WorkManager.getInstance(this)
        workManager.cancelUniqueWork(
            SynchronizeServiceWorker::class.java.name
        )
    }
}