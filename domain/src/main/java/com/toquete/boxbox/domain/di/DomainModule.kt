package com.toquete.boxbox.domain.di

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import org.koin.dsl.module

val domainModule = module {
    single { Clock.System }
    single { TimeZone.currentSystemDefault() }
}
