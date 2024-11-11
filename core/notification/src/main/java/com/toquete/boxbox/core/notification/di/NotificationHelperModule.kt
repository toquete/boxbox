package com.toquete.boxbox.core.notification.di

import com.toquete.boxbox.core.notification.DefaultNotificationHelper
import com.toquete.boxbox.core.notification.NotificationHelper
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class NotificationHelperModule {

    @Binds
    abstract fun bindsNotificationHelper(
        defaultNotificationHelper: DefaultNotificationHelper
    ): NotificationHelper
}
