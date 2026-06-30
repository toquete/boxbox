package com.toquete.boxbox.domain.di

import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import com.toquete.boxbox.domain.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import com.toquete.boxbox.domain.usecase.GetUpcomingRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.IsSyncAllowedUseCase
import kotlinx.datetime.TimeZone
import org.koin.dsl.module
import kotlin.time.Clock

val domainModule = module {
    single<Clock> { Clock.System }
    single<TimeZone> { TimeZone.currentSystemDefault() }

    factory { GetTodayLocalDateUseCase(get()) }
    factory { GetPastRacesInCurrentSeasonUseCase(get(), get()) }
    factory { GetUpcomingRacesInCurrentSeasonUseCase(get(), get()) }
    factory { GetCurrentSeasonRaceResultsUseCase(get(), get()) }
    factory { GetCurrentSeasonSprintResultsUseCase(get(), get()) }
    factory { IsSyncAllowedUseCase(get(), get()) }
}
