package com.toquete.boxbox.core.notification.di

import com.toquete.boxbox.core.notification.DefaultNotificationScheduler
import com.toquete.boxbox.core.notification.NotificationScheduler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationSchedulerModule {

    @Binds
    abstract fun bindNotificationScheduler(
        defaultNotificationScheduler: DefaultNotificationScheduler
    ): NotificationScheduler
}
