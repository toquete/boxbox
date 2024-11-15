package com.toquete.boxbox.core.notification

import com.toquete.boxbox.core.notification.model.Notification

interface NotificationHelper {
    fun notify(notification: Notification)
}
