package com.toquete.boxbox.core.alarm.di

import android.app.AlarmManager
import android.content.Context
import com.toquete.boxbox.core.alarm.AlarmScheduler
import com.toquete.boxbox.core.alarm.DefaultAlarmScheduler
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val alarmModule = module {
    single { androidApplication().getSystemService(Context.ALARM_SERVICE) as AlarmManager }
    single<AlarmScheduler> { DefaultAlarmScheduler(context = androidApplication(), alarmManager = get()) }
}
