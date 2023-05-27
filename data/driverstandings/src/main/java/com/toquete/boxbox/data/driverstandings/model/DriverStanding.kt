package com.toquete.boxbox.data.driverstandings.model

import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.core.network.model.DriverStandingResponse

internal fun DriverStandingResponse.toEntity(): DriverStandingEntity {
    return DriverStandingEntity(
        position = position.toInt(),
        points = points,
        wins = wins,
        driverId = driver.id,
        constructorId = constructors.first().id
    )
}

internal fun FullDriverStandingEntity.toDomain(): FullDriverStanding {
    return FullDriverStanding(
        position = standingEntity.position,
        points = standingEntity.points,
        wins = standingEntity.wins,
        driver = Driver(
            id = driverWithCountryFlagEntity.driver.id,
            firstName = driverWithCountryFlagEntity.driver.firstName,
            lastName = driverWithCountryFlagEntity.driver.lastName,
            imageUrl = driverImageUrl,
            flagUrl = driverWithCountryFlagEntity.flagUrl
        ),
        constructor = Constructor(
            id = constructor.id,
            name = constructor.name,
            imageUrl = constructorImageUrl,
            flagUrl = null
        )
    )
}
