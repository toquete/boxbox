package com.toquete.boxbox.standings.driver.data.source.remote

import com.toquete.boxbox.infrastructure.service.F1ServiceFactory
import com.toquete.boxbox.standings.driver.data.source.DriverStandingsDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.service.DriverStandingsService
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DriverStandingsRemoteDataSource(
    private val service: DriverStandingsService = F1ServiceFactory.getBuilder().create(DriverStandingsService::class.java)
) : DriverStandingsDataSource {

    override fun getDriverStandings(): Flow<List<DriverStanding>> = flow {
        service.getDriverStandings()
            .data
            .standingTable
            .standingsLists
            .first()
            .driverStandings
            .map {
                DriverStanding(
                    position = it.position.toInt(),
                    driver = "${it.driver?.givenName} ${it.driver?.familyName}",
                    nationality = it.driver?.nationality ?: String(),
                    car = it.constructors?.first()?.name ?: String(),
                    points = it.points.toInt()
                )
            }
            .apply {
                emit(this)
            }
    }
}