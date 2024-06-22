package com.toquete.boxbox.core.notification

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import com.toquete.boxbox.core.notification.model.AlarmItem
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toInstant
import javax.inject.Inject

internal class DefaultNotificationScheduler @Inject constructor(
    @ApplicationContext private val context: Context,
    private val alarmManager: AlarmManager
) : NotificationScheduler {

    override fun setAndAllowWhileIdle(alarmItem: AlarmItem) {
        alarmManager.setAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            getAlarmTime(alarmItem),
            getPendingIntent(alarmItem)
        )
    }

    override fun cancel(alarmItem: AlarmItem) {
        alarmManager.cancel(getPendingIntent(alarmItem))
    }

    private fun getAlarmTime(alarmItem: AlarmItem): Long {
        return alarmItem.dateTime
            .toInstant(TimeZone.currentSystemDefault())
            .toEpochMilliseconds()
    }

    private fun getPendingIntent(alarmItem: AlarmItem): PendingIntent {
        return PendingIntent.getBroadcast(
            context,
            alarmItem.hashCode(),
            alarmItem.intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }
}
