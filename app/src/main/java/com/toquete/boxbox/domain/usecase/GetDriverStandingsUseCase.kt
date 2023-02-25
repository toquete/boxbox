package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.data.repository.DriverStandingsRepositoryImpl
import com.toquete.boxbox.domain.model.DriverStanding
import com.toquete.boxbox.domain.repository.DriverStandingsRepository

class GetDriverStandingsUseCase(
    private val repository: DriverStandingsRepository = DriverStandingsRepositoryImpl()
) {

    suspend operator fun invoke(): List<DriverStanding> {
        return repository.getDriverStandings()
    }
}