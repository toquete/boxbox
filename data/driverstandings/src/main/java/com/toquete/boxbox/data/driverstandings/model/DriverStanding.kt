package com.toquete.boxbox.data.driverstandings.model

import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.DriverStanding
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

internal fun FullDriverStandingEntity.toDomain(): DriverStanding {
    return DriverStanding(
        position = standing.position,
        points = standing.points,
        wins = standing.wins,
        driver = Driver(
            id = driverWithCountryFlag.driver.id,
            firstName = driverWithCountryFlag.driver.firstName,
            lastName = driverWithCountryFlag.driver.lastName,
            imageUrl = driverImage?.imageUrl,
            flagUrl = driverWithCountryFlag.flagUrl,
            numberUrl = driverImage?.numberUrl
        ),
        constructor = Constructor(
            id = constructor.id,
            name = constructor.name,
            imageUrl = constructorImageUrl,
            flagUrl = null
        )
    )
}
