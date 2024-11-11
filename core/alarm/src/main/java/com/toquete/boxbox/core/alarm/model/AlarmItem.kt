package com.toquete.boxbox.core.alarm.model

import android.content.Intent
import kotlinx.datetime.LocalDateTime

data class AlarmItem(
    val intent: Intent,
    val dateTime: LocalDateTime,
    val message: String
)
