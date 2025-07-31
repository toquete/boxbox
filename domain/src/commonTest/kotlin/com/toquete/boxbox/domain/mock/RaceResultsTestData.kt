package com.toquete.boxbox.domain.mock

import com.toquete.boxbox.core.model.RaceResult

val raceResults = listOf(
    RaceResult(
        season = "2023",
        round = 1,
        position = 1,
        points = 25,
        driver = driver.copy(imageUrl = null, flagUrl = null, numberUrl = null),
        constructor = constructor.copy(imageUrl = null, flagUrl = null, accentColor = null, backgroundColor = null),
        gridPosition = 1,
        laps = "57",
        status = "Finished",
        time = "1:33:56.736"
    )
)
