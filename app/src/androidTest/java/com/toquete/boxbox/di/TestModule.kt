package com.toquete.boxbox.di

import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
import com.toquete.boxbox.core.preferences.di.UserPreferencesRepositoryModule
import com.toquete.boxbox.data.circuitimages.di.CircuitImageRepositoryModule
import com.toquete.boxbox.sync.di.SyncMonitorModule
import com.toquete.boxbox.data.constructorcolors.di.ConstructorColorRepositoryModule
import com.toquete.boxbox.data.constructorimages.di.ConstructorImageRepositoryModule
import com.toquete.boxbox.data.constructorstandings.di.ConstructorStandingsRepositoryModule
import com.toquete.boxbox.data.countries.di.CountryRepositoryModule
import com.toquete.boxbox.data.driverimages.di.DriverImageRepositoryModule
import com.toquete.boxbox.data.driverstandings.di.DriverStandingsRepositoryModule
import com.toquete.boxbox.data.raceresults.di.RaceResultRepositoryModule
import com.toquete.boxbox.data.races.di.RaceRepositoryModule
import com.toquete.boxbox.data.sprintresults.di.SprintResultRepositoryModule
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import com.toquete.boxbox.domain.repository.ConstructorColorRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import com.toquete.boxbox.domain.repository.CountryRepository
import com.toquete.boxbox.domain.repository.DriverImageRepository
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import com.toquete.boxbox.domain.repository.RaceRepository
import com.toquete.boxbox.domain.repository.RaceResultRepository
import com.toquete.boxbox.domain.repository.SprintResultRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import com.toquete.boxbox.fake.FakeCircuitImageRepository
import com.toquete.boxbox.fake.FakeConstructorColorRepository
import com.toquete.boxbox.fake.FakeConstructorImageRepository
import com.toquete.boxbox.fake.FakeConstructorStandingsRepository
import com.toquete.boxbox.fake.FakeCountryRepository
import com.toquete.boxbox.fake.FakeDriverImageRepository
import com.toquete.boxbox.fake.FakeDriverStandingsRepository
import com.toquete.boxbox.fake.FakeNetworkMonitor
import com.toquete.boxbox.fake.FakeRaceRepository
import com.toquete.boxbox.fake.FakeRaceResultRepository
import com.toquete.boxbox.fake.FakeSprintResultRepository
import com.toquete.boxbox.fake.FakeSyncMonitor
import com.toquete.boxbox.fake.FakeUserPreferencesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [
        CircuitImageRepositoryModule::class,
        ConstructorColorRepositoryModule::class,
        ConstructorImageRepositoryModule::class,
        ConstructorStandingsRepositoryModule::class,
        CountryRepositoryModule::class,
        DriverImageRepositoryModule::class,
        DriverStandingsRepositoryModule::class,
        RaceResultRepositoryModule::class,
        RaceRepositoryModule::class,
        SprintResultRepositoryModule::class,
        MonitorModule::class,
        SyncMonitorModule::class,
        UserPreferencesRepositoryModule::class
    ]
)
object TestModule {

    @Provides
    @Singleton
    fun providesCircuitImageRepository(): CircuitImageRepository = FakeCircuitImageRepository()

    @Provides
    @Singleton
    fun providesConstructorColorRepository(): ConstructorColorRepository = FakeConstructorColorRepository()

    @Provides
    @Singleton
    fun providesConstructorImageRepository(): ConstructorImageRepository = FakeConstructorImageRepository()

    @Provides
    @Singleton
    fun providesConstructorStandingsRepository(): ConstructorStandingsRepository = FakeConstructorStandingsRepository()

    @Provides
    @Singleton
    fun providesCountryRepository(): CountryRepository = FakeCountryRepository()

    @Provides
    @Singleton
    fun providesDriverImageRepository(): DriverImageRepository = FakeDriverImageRepository()

    @Provides
    @Singleton
    fun providesDriverStandingsRepository(): DriverStandingsRepository = FakeDriverStandingsRepository()

    @Provides
    @Singleton
    fun providesRaceResultRepository(): RaceResultRepository = FakeRaceResultRepository()

    @Provides
    @Singleton
    fun providesRaceRepository(): RaceRepository = FakeRaceRepository()

    @Provides
    @Singleton
    fun providesSprintResultRepository(): SprintResultRepository = FakeSprintResultRepository()

    @Provides
    @Singleton
    fun providesNetworkMonitor(): NetworkMonitor = FakeNetworkMonitor()

    @Provides
    @Singleton
    fun providesSyncMonitor(): SyncMonitor = FakeSyncMonitor()

    @Provides
    @Singleton
    fun providesUserPreferencesRepository(): UserPreferencesRepository = FakeUserPreferencesRepository()
}
