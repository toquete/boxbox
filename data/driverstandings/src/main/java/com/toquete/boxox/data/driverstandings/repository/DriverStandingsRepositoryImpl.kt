package com.toquete.boxox.data.driverstandings.repository

import com.toquete.boxbox.model.DriverStanding
import com.toquete.boxbox.preferences.repository.UserPreferencesRepository
import com.toquete.boxox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.datetime.Clock
import javax.inject.Inject
import kotlin.time.Duration.Companion.hours

internal class DriverStandingsRepositoryImpl @Inject constructor(
    private val remoteDataSource: DriverStandingsRemoteDataSource,
    private val localDataSource: DriverStandingsLocalDataSource,
    private val userPreferencesRepository: UserPreferencesRepository
) : DriverStandingsRepository {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        val data = localDataSource.getDriverStandings()

        return if (data.isEmpty() || isDataExpired()) {
            remoteDataSource.getDriverStandings().also {
                localDataSource.deleteAll()
                localDataSource.insertAll(it)
            }
        } else {
            data
        }
    }

    private suspend fun isDataExpired(): Boolean {
        return Clock.System.now().toEpochMilliseconds() - getLastUpdatedTime() > 24L.hours.inWholeMilliseconds
    }

    private suspend fun getLastUpdatedTime(): Long {
        return userPreferencesRepository.userPreferences.first().driverStandingsLastUpdatedTime
    }
}