package com.toquete.boxox.data.driverstandings.source.remote

import com.toquete.boxbox.model.DriverStanding
import com.toquete.boxox.data.driverstandings.source.remote.model.StandingResponse
import com.toquete.boxox.data.driverstandings.source.remote.service.DriverStandingsService
import javax.inject.Inject

internal class DriverStandingsRemoteDataSourceImpl @Inject constructor(
    private val service: DriverStandingsService
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

    private fun StandingResponse.toDriverStanding(): DriverStanding {
        return DriverStanding(
            position = position,
            name = driver?.givenName.orEmpty(),
            lastName = driver?.familyName.orEmpty(),
            nationality = driver?.nationality.orEmpty(),
            car = constructors?.first()?.name.orEmpty(),
            points = points
        )
    }
}