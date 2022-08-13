package com.toquete.boxbox.standings.driver.data.source.remote

import com.toquete.boxbox.infrastructure.service.F1ServiceFactory
import com.toquete.boxbox.standings.driver.data.source.DriverStandingsDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.model.DriverStandingsWrapper
import com.toquete.boxbox.standings.driver.data.source.remote.service.DriverStandingsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DriverStandingsRemoteDataSource(
    private val service: DriverStandingsService = F1ServiceFactory.getBuilder().create(DriverStandingsService::class.java)
) : DriverStandingsDataSource {

    override fun getDriverStandings(): Flow<DriverStandingsWrapper> = flow {
        emit(service.getDriverStandings())
    }
}