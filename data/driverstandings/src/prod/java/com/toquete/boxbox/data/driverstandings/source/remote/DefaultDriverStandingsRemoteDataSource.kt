package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.DriverStandingResponse
import javax.inject.Inject

internal class DefaultDriverStandingsRemoteDataSource @Inject constructor(
    private val service: BoxBoxService
) : DriverStandingsRemoteDataSource {

    override suspend fun getDriverStandings(): List<DriverStandingResponse> {
        return service.getDriverStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .driverStandings
    }
}
