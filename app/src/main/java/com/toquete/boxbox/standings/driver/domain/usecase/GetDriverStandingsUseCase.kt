package com.toquete.boxbox.standings.driver.domain.usecase

import com.toquete.boxbox.standings.driver.domain.model.DriverStanding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetDriverStandingsUseCase {

    operator fun invoke(): Flow<List<DriverStanding>> {
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