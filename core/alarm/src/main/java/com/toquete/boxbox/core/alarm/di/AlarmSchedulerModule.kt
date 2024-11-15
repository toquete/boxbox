package com.toquete.boxbox.core.alarm.di

import com.toquete.boxbox.core.alarm.AlarmScheduler
import com.toquete.boxbox.core.alarm.DefaultAlarmScheduler
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal abstract class AlarmSchedulerModule {

    @Binds
    abstract fun bindNotificationScheduler(
        defaultNotificationScheduler: DefaultAlarmScheduler
    ): AlarmScheduler
}
