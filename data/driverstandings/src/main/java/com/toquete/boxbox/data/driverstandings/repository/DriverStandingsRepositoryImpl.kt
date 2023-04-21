package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.data.driverstandings.source.local.DriverStandingsLocalDataSource
import com.toquete.boxbox.model.DriverStanding
import javax.inject.Inject

internal class DriverStandingsRepositoryImpl @Inject constructor(
    private val localDataSource: DriverStandingsLocalDataSource
) : DriverStandingsRepository {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return localDataSource.getDriverStandings()
    }

    override suspend fun insertAll(driverStandings: List<DriverStanding>) {
        localDataSource.insertAll(driverStandings)
    }

    override suspend fun deleteAll() {
        localDataSource.deleteAll()
    }
}