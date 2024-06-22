package com.toquete.boxbox.core.notification.model

import android.content.Intent
import kotlinx.datetime.LocalDateTime

data class AlarmItem(
    val intent: Intent,
    val dateTime: LocalDateTime,
    val message: String
)
