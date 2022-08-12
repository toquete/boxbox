package com.toquete.boxbox.standings.driver.data.repository

import com.toquete.boxbox.standings.driver.data.source.DriverStandingsDataSource
import com.toquete.boxbox.standings.driver.data.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn

class DriverStandingsRepositoryImpl(
    private val dataSource: DriverStandingsDataSource = DriverStandingsRemoteDataSource(),
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : DriverStandingsRepository {

    override fun getDriverStandings(): Flow<List<DriverStanding>> {
        return dataSource.getDriverStandings()
            .flowOn(dispatcher)
    }
}