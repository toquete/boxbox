package com.toquete.boxbox.core.alarm

import com.toquete.boxbox.core.alarm.model.AlarmItem

interface AlarmScheduler {
    fun setAndAllowWhileIdle(alarmItem: AlarmItem)
    fun cancel(alarmItem: AlarmItem)
}
