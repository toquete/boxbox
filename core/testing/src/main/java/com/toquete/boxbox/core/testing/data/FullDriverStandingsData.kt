package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.DriverWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.FullDriverStanding

val fullDriverStandingEntities = listOf(
    FullDriverStandingEntity(
        driverStandingEntities.first(),
        DriverWithCountryFlagEntity(
            driverEntities.first(),
            countryEntities.first().flagUrl
        ),
        constructorEntities.first(),
        driverImageEntities.first().imageUrl,
        constructorImageEntities.first().imageUrl
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
