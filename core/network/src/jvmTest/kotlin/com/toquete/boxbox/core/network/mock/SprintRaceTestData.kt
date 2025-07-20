@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.network.mock

import com.toquete.boxbox.core.network.model.ConstructorResponse
import com.toquete.boxbox.core.network.model.DriverResponse
import com.toquete.boxbox.core.network.model.RaceResponse
import com.toquete.boxbox.core.network.model.RaceResultResponse
import com.toquete.boxbox.core.network.model.TimeResponse
import kotlin.time.ExperimentalTime

val sprintRacesResponse = listOf(
    RaceResponse(
        season = "2023",
        round = "1",
        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        raceName = "Bahrain Grand Prix",
        circuit = circuitResponse,
        date = "2023-03-05",
        time = "15:00:00Z",
        results = null,
        firstPractice = null,
        secondPractice = null,
        thirdPractice = null,
        qualifying = null,
        sprint = null,
        sprintResults = listOf(
            RaceResultResponse(
                racePosition = "1",
                points = "25",
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
                constructor = ConstructorResponse(
                    id = "red_bull",
                    url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
                    name = "Red Bull",
                    nationality = "Austrian"
                ),
                gridPosition = "1",
                laps = "57",
                status = "Finished",
                time = TimeResponse(
                    millis = "5636736",
                    time = "1:33:56.736"
                )
            )
        )
    )
)
