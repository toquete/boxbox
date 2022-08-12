package com.toquete.boxbox.standings.drivers.domain.usecase

import com.toquete.boxbox.standings.drivers.domain.model.DriverStanding
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class GetDriversStandingUseCase {

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