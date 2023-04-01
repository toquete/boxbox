package com.toquete.boxox.data.driverstandings.source.remote

import com.toquete.boxbox.model.DriverStanding

interface DriverStandingsRemoteDataSource {

    suspend fun getDriverStandings(): List<DriverStanding>
}