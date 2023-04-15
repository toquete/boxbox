package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.model.DriverStanding

internal interface DriverStandingsRemoteDataSource {

    suspend fun getDriverStandings(): List<DriverStanding>
}