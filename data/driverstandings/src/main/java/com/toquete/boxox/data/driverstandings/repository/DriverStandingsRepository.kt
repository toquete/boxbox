package com.toquete.boxox.data.driverstandings.repository

import com.toquete.boxbox.model.DriverStanding

interface DriverStandingsRepository {

    suspend fun getDriverStandings(): List<DriverStanding>
}