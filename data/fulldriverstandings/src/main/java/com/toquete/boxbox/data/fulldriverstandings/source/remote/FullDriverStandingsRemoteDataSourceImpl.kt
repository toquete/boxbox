package com.toquete.boxbox.data.fulldriverstandings.source.remote

import com.toquete.boxbox.model.FullDriverStanding
import com.toquete.boxbox.network.BoxBoxService
import com.toquete.boxbox.network.model.toFullDriverStanding
import javax.inject.Inject

internal class FullDriverStandingsRemoteDataSourceImpl @Inject constructor(
    private val service: BoxBoxService
) : FullDriverStandingsRemoteDataSource {

    override suspend fun getFullDriverStandings(): List<FullDriverStanding> {
        return service.getDriverStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .driverStandings
            .map { it.toFullDriverStanding() }
    }
}