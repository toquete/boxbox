package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity

val raceResultsWithDriverAndConstructor = listOf(
    RaceResultWithDriverAndConstructorEntity(
        raceResult = raceResultEntities.first(),
        driver = driverEntities.first(),
        constructor = constructorEntities.first()
    )
)
