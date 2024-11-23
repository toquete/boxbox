package com.toquete.boxbox.fake

import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.core.testing.data.driverStandings
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeDriverStandingsRepository : DriverStandingsRepository {
    override fun getDriverStandings(): Flow<List<DriverStanding>> {
        return flowOf(driverStandings)
    }

    override suspend fun sync() = Unit
}
