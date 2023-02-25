package com.toquete.boxbox.domain.usecase

import com.toquete.boxbox.domain.model.DriverStanding
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import javax.inject.Inject

class GetDriverStandingsUseCase @Inject constructor(
    private val repository: DriverStandingsRepository
) {

    suspend operator fun invoke(): List<DriverStanding> {
        return repository.getDriverStandings()
    }
}