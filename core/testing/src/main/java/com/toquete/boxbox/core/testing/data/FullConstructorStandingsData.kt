package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.FullConstructorStanding

val fullConstructorStandingEntities = listOf(
    FullConstructorStandingEntity(
        position = 1,
        points = "100",
        wins = "5",
        constructorId = "red_bull",
        constructorName = "Red Bull",
        imageUrl = "http://constructor.com"
    )
)

val fullConstructorStandings = listOf(
    FullConstructorStanding(
        position = 1,
        points = "100",
        wins = "5",
        constructor = constructor
    )
)