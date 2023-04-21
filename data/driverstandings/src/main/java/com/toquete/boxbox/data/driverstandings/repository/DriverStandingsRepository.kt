package com.toquete.boxbox.data.driverstandings.repository

import com.toquete.boxbox.model.DriverStanding

interface DriverStandingsRepository {

    suspend fun getDriverStandings(): List<DriverStanding>

    suspend fun insertAll(driverStandings: List<DriverStanding>)

    suspend fun deleteAll()
}