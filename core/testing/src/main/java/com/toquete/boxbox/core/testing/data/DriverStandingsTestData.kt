package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.network.model.ConstructorResponse
import com.toquete.boxbox.network.model.DriverResponse
import com.toquete.boxbox.network.model.DriverStandingResponse
import com.toquete.boxbox.network.model.DriverStandingsResponse
import com.toquete.boxbox.network.model.DriverStandingsWrapper
import com.toquete.boxbox.network.model.StandingsLists
import com.toquete.boxbox.network.model.StandingsTableResponse

val driverStandingsResponse = listOf(
    DriverStandingResponse(
        position = "1",
        points = "69",
        wins = "2",
        driver = DriverResponse(
            id = "max_verstappen",
            number = "33",
            code = "VER",
            url = "http://en.wikipedia.org/wiki/Max_Verstappen",
            givenName = "Max",
            familyName = "Verstappen",
            dateOfBirth = "1997-09-30",
            nationality = "Dutch"
        ),
        constructors = listOf(
            ConstructorResponse(
                id = "red_bull",
                url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
                name = "Red Bull",
                nationality = "Austrian"
            )
        )
    )
)
val driverStandingsWrapper = DriverStandingsWrapper(
    data = StandingsTableResponse(
        standingTable = StandingsLists(
            standingsLists = listOf(
                DriverStandingsResponse(
                    driverStandings = driverStandingsResponse
                )
            )
        )
    )
)

val driverStandingEntities = listOf(
    DriverStandingEntity(
        position = 1,
        points = "69",
        wins = "2",
        driverId = "max_verstappen",
        constructorId = "red_bull"
    )
)
