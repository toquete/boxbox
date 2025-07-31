package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.SprintRaceResultEntity

val sprintRaceResultEntities = listOf(
    SprintRaceResultEntity(
        season = "2023",
        round = 1,
        position = 1,
        points = 25,
        driverId = "max_verstappen",
        constructorId = "red_bull",
        gridPosition = 1,
        laps = "57",
        status = "Finished",
        time = "1:33:56.736"
    )
)
