package com.manuflowers.photoinspiration.util

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import com.manuflowers.photoinspiration.R
import com.manuflowers.photoinspiration.worker.NOTIFICATION_CHANNEL_ID
import com.manuflowers.photoinspiration.worker.NOTIFICATION_CHANNEL_NAME

fun showNotification(context: Context) {
    val notificationManager = (context.getSystemService(Context.NOTIFICATION_SERVICE)) as NotificationManager

    createNotificationChannel(notificationManager)

    val builder = NotificationCompat.Builder(context, NOTIFICATION_CHANNEL_ID)
        .setSmallIcon(R.drawable.ic_app)
        .setContentTitle("Synchronization service")
        .setContentText("Synchronizing home")
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .build()

    notificationManager.notify(1, builder)

}

private fun createNotificationChannel(manager: NotificationManager?) {
    if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O) {
        val serviceChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        manager?.createNotificationChannel(serviceChannel)
    }
}