package com.toquete.boxbox.standings.driver.domain.usecase

import com.toquete.boxbox.standings.driver.data.repository.DriverStandingsRepositoryImpl
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.flow.Flow

class GetDriverStandingsUseCase(
    private val repository: DriverStandingsRepository = DriverStandingsRepositoryImpl()
) {

    operator fun invoke(): Flow<List<DriverStanding>> {
        return repository.getDriverStandings()
    }
}