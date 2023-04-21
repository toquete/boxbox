package com.toquete.boxbox.data.fulldriverstandings.model

import com.toquete.boxbox.database.model.DriverStandingEntity
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.Driver
import com.toquete.boxbox.model.FullDriverStanding
import com.toquete.boxbox.network.model.DriverStandingResponse

internal fun DriverStandingResponse.toEntity(): DriverStandingEntity {
    return DriverStandingEntity(
        position = position.toInt(),
        points = points,
        driverId = driver.id,
        constructorId = constructors.first().id
    )
}

internal fun DriverStandingResponse.toDomain(): FullDriverStanding {
    return FullDriverStanding(
        position = position.toInt(),
        points = points,
        driver = Driver(
            id = driver.id,
            firstName = driver.givenName,
            lastName = driver.familyName,
            imageUrl = null,
            flagUrl = null
        ),
        constructor = Constructor(
            id = constructors.first().id,
            name = constructors.first().name,
            imageUrl = null
        )
    )
}