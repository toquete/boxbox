package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.ConstructorWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity

val fullConstructorStandingEntities = listOf(
    FullConstructorStandingEntity(
        constructorStandingEntities.first(),
        ConstructorWithCountryFlagEntity(
            constructorEntities.first(),
            countryEntities[1].flagUrl
        ),
        constructorImageEntities.first().imageUrl
    )
)
