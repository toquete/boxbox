package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.network.model.PracticeResponse
import com.toquete.boxbox.core.network.model.RaceResponse

val racesResponse = listOf(
    RaceResponse(
        season = "2023",
        round = "1",
        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        raceName = "Bahrain Grand Prix",
        circuit = circuitResponse,
        date = "2023-03-05",
        time = "15:00:00Z",
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
        )
    )
)

val raceEntities = listOf(
    RaceEntity(
        season = "2023",
        round = 1,
        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        name = "Bahrain Grand Prix",
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
        qualifyingTime = "15:00:00Z"
    )
)
