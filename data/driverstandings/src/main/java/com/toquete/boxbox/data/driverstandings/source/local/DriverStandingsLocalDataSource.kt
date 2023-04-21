package com.toquete.boxbox.data.driverstandings.source.local

import com.toquete.boxbox.database.model.DriverStandingEntity

interface DriverStandingsLocalDataSource {

    suspend fun insertAll(driverStandings: List<DriverStandingEntity>)
}