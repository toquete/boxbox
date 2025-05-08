package com.toquete.boxbox.domain.di

import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import com.toquete.boxbox.domain.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import com.toquete.boxbox.domain.usecase.GetUpcomingRacesInCurrentSeasonUseCase
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val domainModule = module {
    single<Clock> { Clock.System }
    single<TimeZone> { TimeZone.currentSystemDefault() }
    singleOf(::GetCurrentSeasonRaceResultsUseCase)
    singleOf(::GetCurrentSeasonSprintResultsUseCase)
    singleOf(::GetPastRacesInCurrentSeasonUseCase)
    singleOf(::GetTodayLocalDateUseCase)
    singleOf(::GetUpcomingRacesInCurrentSeasonUseCase)
}
