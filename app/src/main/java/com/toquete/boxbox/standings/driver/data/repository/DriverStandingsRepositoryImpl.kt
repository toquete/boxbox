package com.toquete.boxbox.standings.driver.data.repository

import com.toquete.boxbox.standings.driver.data.source.DriverStandingsDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.model.StandingResponse
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DriverStandingsRepositoryImpl(
    private val dataSource: DriverStandingsDataSource = DriverStandingsRemoteDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DriverStandingsRepository {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return withContext(dispatcher) {
            dataSource.getDriverStandings()
                .data
                .standingTable
                .standingsLists
                .first()
                .driverStandings
                .map { it.toDriverStanding() }
        }
    }

    private fun StandingResponse.toDriverStanding(): DriverStanding {
        return DriverStanding(
            position = position.toInt(),
            driver = "${driver?.givenName} ${driver?.familyName}",
            nationality = driver?.nationality ?: String(),
            car = constructors?.first()?.name ?: String(),
            points = points.toInt()
        )
    }
}