package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.ConstructorWithCountryFlagEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.database.model.NewFullConstructorStandingEntity
import com.toquete.boxbox.core.model.FullConstructorStanding

val fullConstructorStandingEntities = listOf(
    FullConstructorStandingEntity(
        position = 1,
        points = "123",
        wins = "3",
        constructorId = "red_bull",
        constructorName = "Red Bull",
        imageUrl = "http://constructor.com",
        flagUrl = "http://flag.com"
    )
)

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
