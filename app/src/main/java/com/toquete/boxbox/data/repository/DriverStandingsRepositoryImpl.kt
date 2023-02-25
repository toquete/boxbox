package com.toquete.boxbox.data.repository

import com.toquete.boxbox.data.source.DriverStandingsDataSource
import com.toquete.boxbox.domain.model.DriverStanding
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import com.toquete.boxbox.infrastructure.di.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val dataSource: DriverStandingsDataSource,
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : DriverStandingsRepository {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return withContext(dispatcher) {
            dataSource.getDriverStandings()
        }
    }
}