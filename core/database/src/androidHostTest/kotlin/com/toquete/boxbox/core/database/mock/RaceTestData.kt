@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.RaceEntity
import kotlin.time.ExperimentalTime

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
