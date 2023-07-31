package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.ConstructorWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.ConstructorStanding

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

val constructorStandings = listOf(
    ConstructorStanding(
        position = 1,
        points = "123",
        wins = "3",
        constructor = constructor
    )
)
