package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.model.DriverStanding
import com.toquete.boxbox.network.BoxBoxService
import com.toquete.boxbox.network.model.toDriverStanding
import javax.inject.Inject

internal class DriverStandingsRemoteDataSourceImpl @Inject constructor(
    private val service: BoxBoxService
) : DriverStandingsRemoteDataSource {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return service.getDriverStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .driverStandings
            .map { it.toDriverStanding() }
    }
}