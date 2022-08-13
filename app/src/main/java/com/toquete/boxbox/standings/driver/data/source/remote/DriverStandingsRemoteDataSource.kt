package com.toquete.boxbox.standings.driver.data.source.remote

import com.toquete.boxbox.infrastructure.service.F1ServiceFactory
import com.toquete.boxbox.standings.driver.data.source.DriverStandingsDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.model.DriverStandingsWrapper
import com.toquete.boxbox.standings.driver.data.source.remote.service.DriverStandingsService

class DriverStandingsRemoteDataSource(
    private val service: DriverStandingsService = F1ServiceFactory.getBuilder().create(DriverStandingsService::class.java)
) : DriverStandingsDataSource {

    override suspend fun getDriverStandings(): DriverStandingsWrapper {
        return service.getDriverStandings()
    }
}