package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.FullConstructorStanding

val fullConstructorStandingEntities = listOf(
    FullConstructorStandingEntity(
        position = 1,
        points = "123",
        wins = "3",
        constructorId = "red_bull",
        constructorName = "Red Bull",
        imageUrl = "http://constructor.com"
    )
)

val fullConstructorStandings = listOf(
    FullConstructorStanding(
        position = 1,
        points = "123",
        wins = "3",
        constructor = constructor
    )
)
