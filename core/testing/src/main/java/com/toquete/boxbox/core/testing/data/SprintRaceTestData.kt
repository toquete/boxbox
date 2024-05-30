package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.network.model.ConstructorResponse
import com.toquete.boxbox.core.network.model.DriverResponse
import com.toquete.boxbox.core.network.model.RaceResponse
import com.toquete.boxbox.core.network.model.RaceResultResponse
import com.toquete.boxbox.core.network.model.TimeResponse
import kotlinx.datetime.toInstant

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

val sprintRaceEntities = listOf(
    RaceEntity(
        season = "2023",
        round = 1,
        raceUrl = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        raceName = "Bahrain Grand Prix",
        circuitId = "bahrain",
        date = "2023-03-05",
        time = "15:00:00Z",
        firstPracticeDate = "2023-03-03",
        firstPracticeTime = "11:30:00Z",
        secondPracticeDate = "2023-03-03",
        secondPracticeTime = "15:00:00Z",
        thirdPracticeDate = "2023-03-04",
        thirdPracticeTime = "11:30:00Z",
        qualifyingDate = "2023-03-04",
        qualifyingTime = "15:00:00Z",
        sprintDate = null,
        sprintTime = null
    ),
    RaceEntity(
        season = "2023",
        round = 18,
        raceUrl = "https://en.wikipedia.org/wiki/2023_United_States_Grand_Prix",
        raceName = "United States Grand Prix",
        circuitId = "americas",
        date = "2023-10-22",
        time = "19:00:00Z",
        firstPracticeDate = "2023-10-20",
        firstPracticeTime = "17:30:00Z",
        secondPracticeDate = "2023-10-21",
        secondPracticeTime = "18:00:00Z",
        thirdPracticeDate = null,
        thirdPracticeTime = null,
        qualifyingDate = "2023-10-20",
        qualifyingTime = "21:00:00Z",
        sprintDate = "2023-10-21",
        sprintTime = "22:00:00Z"
    )
)

val sprintRaces = listOf(
    Race(
        season = "2023",
        round = 1,
        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        name = "Bahrain Grand Prix",
        circuit = circuits.first(),
        dateTime = "2023-03-05T15:00:00Z".toInstant(),
        firstPracticeDateTime = "2023-03-03T11:30:00Z".toInstant(),
        secondPracticeDateTime = "2023-03-03T15:00:00Z".toInstant(),
        thirdPracticeDateTime = "2023-03-04T11:30:00Z".toInstant(),
        qualifyingDateTime = "2023-03-04T15:00:00Z".toInstant(),
        sprintDateTime = null
    ),
    Race(
        season = "2023",
        round = 18,
        url = "https://en.wikipedia.org/wiki/2023_United_States_Grand_Prix",
        name = "United States Grand Prix",
        circuit = circuits.last(),
        dateTime = "2023-10-22T19:00:00Z".toInstant(),
        firstPracticeDateTime = "2023-10-20T17:30:00Z".toInstant(),
        secondPracticeDateTime = "2023-10-21T18:00:00Z".toInstant(),
        thirdPracticeDateTime = null,
        qualifyingDateTime = "2023-10-20T21:00:00Z".toInstant(),
        sprintDateTime = "2023-10-21T22:00:00Z".toInstant()
    )
)
