package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.FullDriverStanding

val fullDriverStandingEntities = listOf(
    FullDriverStandingEntity(
        position = 1,
        points = "90",
        wins = "5",
        driverId = "max_verstappen",
        firstName = "Max",
        lastName = "Verstappen",
        imageUrl = "http://image.com",
        flagUrl = "http://flag.com",
        constructorId = "red_bull",
        constructorName = "Red Bull",
        constructorImageUrl = "http://constructor.com"
    )
)

val fullDriverStandings = listOf(
    FullDriverStanding(
        position = 1,
        points = "90",
        wins = "5",
        driver = driver,
        constructor = constructor
    )
)