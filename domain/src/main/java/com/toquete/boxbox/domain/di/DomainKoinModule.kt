package com.toquete.boxbox.domain.di

import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import com.toquete.boxbox.domain.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import com.toquete.boxbox.domain.usecase.GetUpcomingRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.IsSyncAllowedUseCase
import kotlinx.datetime.TimeZone
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import kotlin.time.Clock

val domainModule = module {
    single<Clock> { Clock.System }
    single<TimeZone> { TimeZone.currentSystemDefault() }

    factoryOf(::GetTodayLocalDateUseCase)
    factoryOf(::GetPastRacesInCurrentSeasonUseCase)
    factoryOf(::GetUpcomingRacesInCurrentSeasonUseCase)
    factoryOf(::GetCurrentSeasonRaceResultsUseCase)
    factoryOf(::GetCurrentSeasonSprintResultsUseCase)
    factoryOf(::IsSyncAllowedUseCase)
}
