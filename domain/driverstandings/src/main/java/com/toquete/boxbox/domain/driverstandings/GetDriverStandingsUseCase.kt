package com.toquete.boxbox.domain.driverstandings

import com.toquete.boxbox.model.DriverStanding
import com.toquete.boxox.data.driverstandings.repository.DriverStandingsRepository
import javax.inject.Inject

class GetDriverStandingsUseCase @Inject constructor(
    private val repository: DriverStandingsRepository
) {

    suspend operator fun invoke(): List<DriverStanding> {
        return repository.getDriverStandings()
    }
}