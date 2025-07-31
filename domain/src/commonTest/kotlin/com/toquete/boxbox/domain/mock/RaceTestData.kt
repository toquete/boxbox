@file:OptIn(ExperimentalTime::class)

package com.toquete.boxbox.domain.mock

import com.toquete.boxbox.core.model.Race
import kotlin.time.ExperimentalTime
import kotlin.time.Instant

val races = listOf(
    Race(
        season = "2023",
        round = 1,
        url = "https://en.wikipedia.org/wiki/2023_Bahrain_Grand_Prix",
        name = "Bahrain Grand Prix",
        circuit = circuits.first(),
        dateTime = Instant.parse("2023-03-05T15:00:00Z"),
        firstPracticeDateTime = Instant.parse("2023-03-03T11:30:00Z"),
        secondPracticeDateTime = Instant.parse("2023-03-03T15:00:00Z"),
        thirdPracticeDateTime = Instant.parse("2023-03-04T11:30:00Z"),
        qualifyingDateTime = Instant.parse("2023-03-04T15:00:00Z"),
        sprintDateTime = null
    ),
    Race(
        season = "2023",
        round = 18,
        url = "https://en.wikipedia.org/wiki/2023_United_States_Grand_Prix",
        name = "United States Grand Prix",
        circuit = circuits.last(),
        dateTime = Instant.parse("2023-10-22T19:00:00Z"),
        firstPracticeDateTime = Instant.parse("2023-10-20T17:30:00Z"),
        secondPracticeDateTime = Instant.parse("2023-10-21T18:00:00Z"),
        thirdPracticeDateTime = null,
        qualifyingDateTime = Instant.parse("2023-10-20T21:00:00Z"),
        sprintDateTime = Instant.parse("2023-10-21T22:00:00Z")
    )
)
