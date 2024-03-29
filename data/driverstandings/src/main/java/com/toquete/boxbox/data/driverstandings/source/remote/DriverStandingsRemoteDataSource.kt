package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.network.model.DriverStandingResponse

internal interface DriverStandingsRemoteDataSource {

    suspend fun getDriverStandings(): List<DriverStandingResponse>
}
