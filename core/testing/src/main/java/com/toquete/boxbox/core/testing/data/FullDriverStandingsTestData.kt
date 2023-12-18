package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.DriverWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.DriverStanding

val fullDriverStandingEntities = listOf(
    FullDriverStandingEntity(
        driverStandingEntities.first(),
        DriverWithCountryFlagEntity(
            driverEntities.first(),
            countryEntities.first().flagUrl
        ),
        constructorEntities.first(),
        driverImageEntities.first(),
        constructorImageEntities.first().imageUrl
    )
)

val driverStandings = listOf(
    DriverStanding(
        position = 1,
        points = "90",
        wins = "5",
        driver = driver,
        constructor = constructor.copy(flagUrl = null)
    )
)
