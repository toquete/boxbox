package com.toquete.boxbox.di

import com.toquete.boxbox.core.common.util.NetworkMonitor
import com.toquete.boxbox.core.common.util.SyncMonitor
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
import org.koin.dsl.module

val testModule = module {
    single<CircuitImageRepository> { FakeCircuitImageRepository() }
    single<ConstructorColorRepository> { FakeConstructorColorRepository() }
    single<ConstructorImageRepository> { FakeConstructorImageRepository() }
    single<ConstructorStandingsRepository> { FakeConstructorStandingsRepository() }
    single<CountryRepository> { FakeCountryRepository() }
    single<DriverImageRepository> { FakeDriverImageRepository() }
    single<DriverStandingsRepository> { FakeDriverStandingsRepository() }
    single<RaceResultRepository> { FakeRaceResultRepository() }
    single<RaceRepository> { FakeRaceRepository() }
    single<SprintResultRepository> { FakeSprintResultRepository() }
    single<NetworkMonitor> { FakeNetworkMonitor() }
    single<SyncMonitor> { FakeSyncMonitor() }
    single<UserPreferencesRepository> { FakeUserPreferencesRepository() }
}
