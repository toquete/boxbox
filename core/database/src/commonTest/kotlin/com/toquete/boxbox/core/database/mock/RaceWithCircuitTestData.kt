package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity

val racesWithCircuits = listOf(
    RaceWithCircuitEntity(
        race = raceEntities.first(),
        circuit = circuitEntities.first(),
        flagUrl = "http://bahrain.com",
        circuitImageUrl = "http://image.com"
    ),
    RaceWithCircuitEntity(
        race = raceEntities.last(),
        circuit = circuitEntities.last(),
        flagUrl = "http://america.com",
        circuitImageUrl = "http://image.com"
    )
)
