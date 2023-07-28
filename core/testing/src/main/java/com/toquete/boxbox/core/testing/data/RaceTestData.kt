package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.network.model.PracticeResponse
import com.toquete.boxbox.core.network.model.RaceResponse

val raceResponse = RaceResponse(
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
