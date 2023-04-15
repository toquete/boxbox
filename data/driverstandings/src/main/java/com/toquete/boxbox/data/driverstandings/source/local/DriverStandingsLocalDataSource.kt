package com.toquete.boxbox.data.driverstandings.source.local

import com.toquete.boxbox.model.DriverStanding

internal interface DriverStandingsLocalDataSource {

    suspend fun getDriverStandings(): List<DriverStanding>

    suspend fun insertAll(driverStandings: List<DriverStanding>)

    suspend fun deleteAll()
}