package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.DriverWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity

val fullDriverStandingEntities = listOf(
    FullDriverStandingEntity(
        driverStandingEntities.first(),
        DriverWithCountryFlagEntity(
            driverEntities.first(),
            countryEntities.first().flagUrl
        ),
        constructorEntities.first(),
        driverImageEntities.first(),
        constructorImageEntities.first().imageUrl,
        constructorColorEntities.first()
    )
)
