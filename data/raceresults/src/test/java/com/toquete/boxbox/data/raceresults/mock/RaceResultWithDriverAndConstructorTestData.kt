package com.toquete.boxbox.data.raceresults.mock

import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.driverEntities
import com.toquete.boxbox.core.testing.data.raceResultEntities

val raceResultsWithDriverAndConstructor = listOf(
    RaceResultWithDriverAndConstructorEntity(
        raceResult = raceResultEntities.first(),
        driver = driverEntities.first(),
        constructor = constructorEntities.first()
    )
)
