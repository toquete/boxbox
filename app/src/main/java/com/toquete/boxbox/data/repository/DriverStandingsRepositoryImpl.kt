package com.toquete.boxbox.data.repository

import com.toquete.boxbox.data.source.DriverStandingsDataSource
import com.toquete.boxbox.domain.model.DriverStanding
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import javax.inject.Inject

class DriverStandingsRepositoryImpl @Inject constructor(
    private val dataSource: DriverStandingsDataSource
) : DriverStandingsRepository {

    override suspend fun getDriverStandings(): List<DriverStanding> {
        return dataSource.getDriverStandings()
    }
}