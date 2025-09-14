package com.toquete.boxbox.data.driverstandings.fake

import com.toquete.boxbox.core.network.model.DriverStandingResponse
import com.toquete.boxbox.data.driverstandings.mock.driverStandingsResponse
import com.toquete.boxbox.data.driverstandings.source.remote.DriverStandingsRemoteDataSource

class FakeDriverStandingsRemoteDataSource : DriverStandingsRemoteDataSource {
    override suspend fun getDriverStandings(): List<DriverStandingResponse> = driverStandingsResponse
}
