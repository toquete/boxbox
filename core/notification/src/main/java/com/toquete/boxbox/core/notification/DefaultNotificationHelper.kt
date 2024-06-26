package com.toquete.boxbox.core.notification

import android.app.NotificationChannel
import android.os.Build
import androidx.core.app.NotificationManagerCompat
import com.toquete.boxbox.core.notification.model.Notification
import javax.inject.Inject

internal class DefaultNotificationHelper @Inject constructor(
    private val notificationManager: NotificationManagerCompat
) : NotificationHelper {

    override fun notify(notification: Notification) {
        createNotificationChannel(notification)

        if (notificationManager.areNotificationsEnabled()) {
            notificationManager.notify(notification.id, notification.notification)
        }
    }

    private fun createNotificationChannel(notification: Notification) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = notification.channel
            NotificationChannel(channel.id, channel.channelName, channel.importance).apply {
                description = channel.description
                enableLights(channel.enableLights)
                enableVibration(channel.enableVibration)
                notificationManager.createNotificationChannel(this)
            }
        }
    }
}
