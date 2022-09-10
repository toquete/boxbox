package com.toquete.boxbox.standings.driver.domain.usecase

import com.toquete.boxbox.standings.driver.data.repository.DriverStandingsRepositoryImpl
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.repository.DriverStandingsRepository

class GetDriverStandingsUseCase(
    private val repository: DriverStandingsRepository = DriverStandingsRepositoryImpl()
) {

    suspend operator fun invoke(): List<DriverStanding> {
        return repository.getDriverStandings()
    }
}