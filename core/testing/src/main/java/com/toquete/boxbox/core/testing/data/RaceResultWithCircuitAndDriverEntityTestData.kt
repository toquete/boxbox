package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.RaceResultWithCircuitAndDriverEntity

val raceResultsWithCircuitAndDriverEntity = listOf(
    RaceResultWithCircuitAndDriverEntity(
        raceResult = raceResultEntities.first(),
        country = "Bahrain",
        circuitName = "Bahrain International Circuit",
        driverCode = "VER",
        driverImageUrl = "http://image.com"
    )
)
