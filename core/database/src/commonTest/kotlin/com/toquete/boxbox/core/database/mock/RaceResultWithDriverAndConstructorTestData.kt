package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity

val raceResultsWithDriverAndConstructor = listOf(
    RaceResultWithDriverAndConstructorEntity(
        raceResult = raceResultEntities.first(),
        driver = driverEntities.first(),
        constructor = constructorEntities.first()
    )
)
