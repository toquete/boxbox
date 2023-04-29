package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.database.model.FullDriverStandingEntity

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