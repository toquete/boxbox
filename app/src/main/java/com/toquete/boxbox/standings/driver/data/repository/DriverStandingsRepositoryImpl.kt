package com.toquete.boxbox.standings.driver.data.repository

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import com.toquete.boxbox.standings.driver.domain.repository.DriverStandingsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class DriverStandingsRepositoryImpl : DriverStandingsRepository {

    override fun getDriverStandings(): Flow<List<DriverStanding>> {
        return flowOf(
            listOf(
                DriverStanding(
                    position = 1,
                    driver = "Max Verstappen",
                    nationality = "NED",
                    car = "Red Bull",
                    points = 258
                )
            )
        )
    }
}