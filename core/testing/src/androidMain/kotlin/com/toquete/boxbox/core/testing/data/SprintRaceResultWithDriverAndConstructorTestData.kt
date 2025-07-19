package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity

val sprintRaceResultsWithDriverAndConstructor = listOf(
    SprintRaceResultWithDriverAndConstructorEntity(
        sprintRaceResult = sprintRaceResultEntities.first(),
        driver = driverEntities.first(),
        constructor = constructorEntities.first()
    )
)
