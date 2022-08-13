package com.toquete.boxbox.standings.driver.domain.usecase

import com.toquete.boxbox.flags.data.repository.FlagsRepositoryImpl
import com.toquete.boxbox.flags.domain.repository.FlagsRepository
import com.toquete.boxbox.standings.driver.data.repository.DriverStandingsRepositoryImpl
import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.repository.DriverStandingsRepository

class GetDriverStandingsUseCase(
    private val repository: DriverStandingsRepository = DriverStandingsRepositoryImpl(),
    private val flagsRepository: FlagsRepository = FlagsRepositoryImpl()
) {

    suspend operator fun invoke(): List<DriverStanding> {
        return repository.getDriverStandings()
            .map { standings ->
                standings.copy(nationality = flagsRepository.getFlagByDemonym(standings.nationality).png)
            }
    }
}