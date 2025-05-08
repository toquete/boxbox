package com.toquete.boxbox.core.notification.di

import androidx.core.app.NotificationManagerCompat
import com.toquete.boxbox.core.notification.DefaultNotificationHelper
import com.toquete.boxbox.core.notification.NotificationHelper
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val notificationModule = module {
    single { NotificationManagerCompat.from(androidApplication()) }
    single<NotificationHelper> { DefaultNotificationHelper(notificationManager = get()) }
}
