package com.toquete.boxbox.di

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.domain.repository.RaceRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import com.toquete.boxbox.domain.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.GetTodayLocalDateUseCase
import com.toquete.boxbox.domain.usecase.GetUpcomingRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.IsSyncAllowedUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import kotlinx.datetime.TimeZone
import kotlin.coroutines.CoroutineContext
import kotlin.time.Clock
import kotlin.time.ExperimentalTime

// TODO Step 12: delete when Hilt is fully replaced by Koin.
@OptIn(ExperimentalTime::class)
@Module
@InstallIn(SingletonComponent::class)
internal object HiltDomainShimModule {

    @Provides
    fun provideClock(): Clock = Clock.System

    @Provides
    fun provideTimeZone(): TimeZone = TimeZone.currentSystemDefault()

    @Provides
    @IoDispatcher
    @Suppress("InjectDispatcher")
    fun provideIoDispatcher(): CoroutineContext = Dispatchers.IO

    @Provides
    fun provideGetTodayLocalDateUseCase(clock: Clock) =
        GetTodayLocalDateUseCase(clock)

    @Provides
    fun provideGetPastRacesInCurrentSeasonUseCase(
        repository: RaceRepository,
        getTodayLocalDateUseCase: GetTodayLocalDateUseCase
    ) = GetPastRacesInCurrentSeasonUseCase(repository, getTodayLocalDateUseCase)

    @Provides
    fun provideGetUpcomingRacesInCurrentSeasonUseCase(
        repository: RaceRepository,
        getTodayLocalDateUseCase: GetTodayLocalDateUseCase
    ) = GetUpcomingRacesInCurrentSeasonUseCase(repository, getTodayLocalDateUseCase)

    @Provides
    fun provideGetCurrentSeasonRaceResultsUseCase(
        raceResultRepository: RaceResultRepository,
        getTodayLocalDateUseCase: GetTodayLocalDateUseCase
    ) = GetCurrentSeasonRaceResultsUseCase(raceResultRepository, getTodayLocalDateUseCase)

    @Provides
    fun provideGetCurrentSeasonSprintResultsUseCase(
        sprintResultRepository: SprintResultRepository,
        getTodayLocalDateUseCase: GetTodayLocalDateUseCase
    ) = GetCurrentSeasonSprintResultsUseCase(sprintResultRepository, getTodayLocalDateUseCase)

    @Provides
    fun provideIsSyncAllowedUseCase(
        getTodayLocalDateUseCase: GetTodayLocalDateUseCase,
        userPreferencesRepository: UserPreferencesRepository
    ) = IsSyncAllowedUseCase(getTodayLocalDateUseCase, userPreferencesRepository)
}
