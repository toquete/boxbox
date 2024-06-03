package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.network.model.PracticeResponse
import com.toquete.boxbox.core.network.model.RaceResponse
import kotlinx.datetime.toInstant

val racesResponse = listOf(
    RaceResponse(
        season = "2023",
        round = "1",
        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        raceName = "Bahrain Grand Prix",
        circuit = circuitResponse,
        date = "2023-03-05",
        time = "15:00:00Z",
        results = null,
        firstPractice = PracticeResponse(
            date = "2023-03-03",
            time = "11:30:00Z"
        ),
        secondPractice = PracticeResponse(
            date = "2023-03-03",
            time = "15:00:00Z"
        ),
        thirdPractice = PracticeResponse(
            date = "2023-03-04",
            time = "11:30:00Z"
        ),
        qualifying = PracticeResponse(
            date = "2023-03-04",
            time = "15:00:00Z"
        ),
        sprint = null,
        sprintResults = null
    )
)

val raceEntities = listOf(
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

val races = listOf(
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
