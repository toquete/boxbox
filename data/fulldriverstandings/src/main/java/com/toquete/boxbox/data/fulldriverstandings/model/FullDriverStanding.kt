package com.toquete.boxbox.data.fulldriverstandings.model

import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.Driver
import com.toquete.boxbox.model.FullDriverStanding
import com.toquete.boxbox.network.model.DriverStandingResponse

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
        position = position,
        points = points,
        wins = wins,
        driver = Driver(
            id = driverId,
            firstName = firstName,
            lastName = lastName,
            imageUrl = imageUrl,
            flagUrl = flagUrl
        ),
        constructor = Constructor(
            id = constructorId,
            name = constructorName,
            imageUrl = constructorImageUrl
        )
    )
}