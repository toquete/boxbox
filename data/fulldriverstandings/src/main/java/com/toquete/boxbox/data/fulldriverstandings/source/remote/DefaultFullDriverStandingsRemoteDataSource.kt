package com.toquete.boxbox.data.fulldriverstandings.source.remote

import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.model.DriverStandingResponse
import javax.inject.Inject

internal class DefaultFullDriverStandingsRemoteDataSource @Inject constructor(
    private val service: BoxBoxService
) : FullDriverStandingsRemoteDataSource {

    override suspend fun getFullDriverStandings(): List<DriverStandingResponse> {
        return service.getDriverStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .driverStandings
    }
}