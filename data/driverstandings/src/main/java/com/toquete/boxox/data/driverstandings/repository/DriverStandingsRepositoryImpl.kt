package com.toquete.boxox.data.driverstandings.repository

import com.toquete.boxbox.model.DriverStanding
import com.toquete.boxox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource
import javax.inject.Inject

internal class DriverStandingsRepositoryImpl @Inject constructor(
    private val dataSource: DriverStandingsRemoteDataSource
) : DriverStandingsRepository {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return dataSource.getDriverStandings()
    }
}