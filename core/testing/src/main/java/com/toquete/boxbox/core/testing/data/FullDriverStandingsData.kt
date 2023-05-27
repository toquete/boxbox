package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.DriverWithCountryEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.database.model.NewFullDriverStandingEntity
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

val newFullDriverStandingEntities = listOf(
    NewFullDriverStandingEntity(
        driverStandingEntities.first(),
        driverEntities.first(),
        constructorEntities.first(),
        driverImageEntities.first(),
        constructorImageEntities.first(),
        DriverWithCountryEntity(
            driverEntities.first(),
            countryEntities.first()
        )
    )
)

val fullDriverStandings = listOf(
    FullDriverStanding(
        position = 1,
        points = "90",
        wins = "5",
        driver = driver,
        constructor = constructor.copy(flagUrl = null)
    )
)
