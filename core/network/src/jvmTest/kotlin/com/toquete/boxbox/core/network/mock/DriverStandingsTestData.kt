package com.toquete.boxbox.core.network.mock

import com.toquete.boxbox.core.network.model.ConstructorResponse
import com.toquete.boxbox.core.network.model.DriverResponse
import com.toquete.boxbox.core.network.model.DriverStandingResponse
import com.toquete.boxbox.core.network.model.DriverStandingsResponse
import com.toquete.boxbox.core.network.model.DriverStandingsWrapper
import com.toquete.boxbox.core.network.model.StandingsLists
import com.toquete.boxbox.core.network.model.StandingsTableResponse

val driverStandingsResponse = listOf(
    DriverStandingResponse(
        position = "1",
        positionText = "1",
        points = "90",
        wins = "5",
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
