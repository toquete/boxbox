package com.toquete.boxbox.core.notification

import com.toquete.boxbox.core.notification.model.AlarmItem

interface NotificationScheduler {
    fun setAndAllowWhileIdle(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}
