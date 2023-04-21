package com.toquete.boxbox.data.fulldriverstandings.repository

import com.toquete.boxbox.data.drivers.source.local.DriversLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.model.toDomain
import com.toquete.boxbox.data.fulldriverstandings.model.toEntity
import com.toquete.boxbox.data.fulldriverstandings.source.local.FullDriverStandingsLocalDataSource
import com.toquete.boxbox.data.fulldriverstandings.source.remote.FullDriverStandingsRemoteDataSource
import com.toquete.boxbox.database.model.FullDriverStandingEntity
import com.toquete.boxbox.database.model.asFullDomain
import com.toquete.boxbox.model.FullDriverStanding
import com.toquete.boxbox.network.model.DriverStandingResponse
import com.toquete.boxbox.preferences.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

internal class FullDriverStandingsRepositoryImpl @Inject constructor(
    private val remoteDataSource: FullDriverStandingsRemoteDataSource,
    private val localDataSource: FullDriverStandingsLocalDataSource,
    private val driversLocalDataSource: DriversLocalDataSource,
    private val userPreferencesRepository: UserPreferencesRepository
) : FullDriverStandingsRepository {

    override suspend fun getFullDriverStandings(): List<FullDriverStanding> {
        val data = localDataSource.getFullDriverStandings()

        return if (data.isEmpty() || isDataExpired()) {
            remoteDataSource.getFullDriverStandings()
                .also { list ->
                    driversLocalDataSource.insertAll(list.map { it.driver.toEntity() })
                }
                .map(DriverStandingResponse::toDomain)
        } else {
            data.map(FullDriverStandingEntity::asFullDomain)
        }
    }

    private suspend fun isDataExpired(): Boolean {
        return Clock.System.now().toEpochMilliseconds() - getLastUpdatedTime() > 24L.hours.inWholeMilliseconds
    }

    private suspend fun getLastUpdatedTime(): Long {
        return userPreferencesRepository.userPreferences.first().driverStandingsLastUpdatedTime
    }
}