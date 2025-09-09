package com.toquete.boxbox.data.constructorstandings.mock

import com.toquete.boxbox.core.database.model.ConstructorWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.ConstructorStanding
import com.toquete.boxbox.core.testing.data.constructor
import com.toquete.boxbox.core.testing.data.constructorEntities
import com.toquete.boxbox.core.testing.data.constructorImageEntities
import com.toquete.boxbox.core.testing.data.constructorStandingEntities
import com.toquete.boxbox.core.testing.data.countryEntities

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
        constructor = constructor.copy(accentColor = null, backgroundColor = null)
    )
)
