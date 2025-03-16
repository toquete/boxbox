package com.toquete.boxbox.data.driverstandings.model

import com.toquete.boxbox.core.database.model.DriverStandingEntity
import com.toquete.boxbox.core.database.model.FullDriverStandingEntity
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.core.network.model.DriverStandingResponse

internal fun DriverStandingResponse.toEntity(index: Int): DriverStandingEntity {
    return DriverStandingEntity(
        position = position?.toInt() ?: (index + 1),
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
            id = driverWithCountryFlag?.driver?.id.orEmpty(),
            firstName = driverWithCountryFlag?.driver?.firstName.orEmpty(),
            lastName = driverWithCountryFlag?.driver?.lastName.orEmpty(),
            imageUrl = driverImage?.imageUrl,
            flagUrl = driverWithCountryFlag?.flagUrl,
            numberUrl = driverImage?.numberUrl
        ),
        constructor = Constructor(
            id = constructor?.id.orEmpty(),
            name = constructor?.name.orEmpty(),
            imageUrl = constructorImageUrl,
            accentColor = constructorColor?.accentColor,
            backgroundColor = constructorColor?.backgroundColor
        )
    )
}
