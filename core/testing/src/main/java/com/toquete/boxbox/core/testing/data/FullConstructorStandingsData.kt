package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.ConstructorWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.NewFullConstructorStandingEntity
import com.toquete.boxbox.core.model.FullConstructorStanding

val newFullConstructorStandingEntities = listOf(
    NewFullConstructorStandingEntity(
        constructorStandingEntities.first(),
        ConstructorWithCountryFlagEntity(
            constructorEntities.first(),
            countryEntities.last().flagUrl
        ),
        constructorImageEntities.first().imageUrl
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
