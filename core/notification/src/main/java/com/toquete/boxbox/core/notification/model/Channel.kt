package com.toquete.boxbox.core.notification.model

import android.app.NotificationManager

enum class Channel(
    val id: String,
    val channelName: String,
    val description: String,
    val importance: Int,
    val enableLights: Boolean,
    val enableVibration: Boolean
) {
    EVENTS(
        id = "EVENTS_CHANNEL_ID",
        channelName = "Events",
        description = "Events notifications",
        importance = NotificationManager.IMPORTANCE_DEFAULT,
        enableLights = true,
        enableVibration = true
    )
}
