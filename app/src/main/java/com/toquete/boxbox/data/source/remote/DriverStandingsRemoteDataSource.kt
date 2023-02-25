package com.toquete.boxbox.data.source.remote

import com.toquete.boxbox.data.source.DriverStandingsDataSource
import com.toquete.boxbox.data.source.remote.model.StandingResponse
import com.toquete.boxbox.data.source.remote.service.DriverStandingsService
import com.toquete.boxbox.domain.model.DriverStanding
import javax.inject.Inject

class DriverStandingsRemoteDataSource @Inject constructor(
    private val service: DriverStandingsService
) : DriverStandingsDataSource {

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