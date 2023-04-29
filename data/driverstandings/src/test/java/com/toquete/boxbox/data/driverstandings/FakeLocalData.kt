package com.toquete.boxbox.data.driverstandings

import com.toquete.boxbox.database.model.DriverStandingEntity

object FakeLocalData {

    val driverStandings = listOf(
        DriverStandingEntity(
            position = 1,
            points = "100",
            wins = "5",
            driverId = "max_verstappen",
            constructorId = "red_bull"
        )
    )
}