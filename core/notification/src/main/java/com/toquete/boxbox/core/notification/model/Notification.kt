package com.toquete.boxbox.core.notification.model

import android.app.Notification as AndroidNotification

data class Notification(
    val id: Int,
    val channel: Channel,
    val notification: AndroidNotification
)
