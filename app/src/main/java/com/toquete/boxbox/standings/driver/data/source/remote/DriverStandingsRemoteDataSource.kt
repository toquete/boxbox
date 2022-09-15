package com.toquete.boxbox.standings.driver.data.source.remote

import com.toquete.boxbox.infrastructure.service.ServiceFactory
import com.toquete.boxbox.standings.driver.data.source.DriverStandingsDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.model.StandingResponse
import com.toquete.boxbox.standings.driver.data.source.remote.service.DriverStandingsService
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding

class DriverStandingsRemoteDataSource(
    private val service: DriverStandingsService = ServiceFactory.create(DriverStandingsService::class.java)
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
            driver = "${driver?.givenName} ${driver?.familyName}",
            nationality = driver?.nationality ?: String(),
            car = constructors?.first()?.name ?: String(),
            points = points
        )
    }
}