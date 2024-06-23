package com.toquete.boxbox.core.notification

import android.app.NotificationChannel
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.toquete.boxbox.core.notification.model.Notification
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import android.app.Notification as AndroidNotification

internal class DefaultNotificationHelper @Inject constructor(
    @ApplicationContext private val context: Context,
    private val notificationManager: NotificationManagerCompat
) : NotificationHelper {

    override fun notify(notification: Notification) {
        createNotificationChannel(notification)

        if (notificationManager.areNotificationsEnabled()) {
            notificationManager.notify(notification.id, buildNotification(notification))
        }
    }

    private fun buildNotification(notification: Notification): AndroidNotification {
        return NotificationCompat.Builder(context, notification.channel.id)
            .setSmallIcon(notification.smallIcon)
            .setContentTitle(notification.title)
            .setContentText(notification.text)
            .setPriority(notification.priority)
            .setCategory(notification.category)
            .setStyle(notification.style)
            .build()
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
