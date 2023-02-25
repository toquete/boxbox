package com.toquete.boxbox.data.repository

import com.toquete.boxbox.data.source.DriverStandingsDataSource
import com.toquete.boxbox.data.source.remote.DriverStandingsRemoteDataSource
import com.toquete.boxbox.domain.model.DriverStanding
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
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
        }
    }
}