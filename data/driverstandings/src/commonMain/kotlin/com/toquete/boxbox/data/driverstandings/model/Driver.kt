package com.toquete.boxbox.data.driverstandings.model

import com.toquete.boxbox.core.database.model.DriverEntity
import com.toquete.boxbox.core.network.model.DriverResponse

internal fun DriverResponse.toEntity(): DriverEntity {
    return DriverEntity(
        id = id,
        number = number,
        code = code,
        url = url,
        firstName = givenName,
        lastName = familyName,
        dateOfBirth = dateOfBirth,
        nationality = nationality
    )
}
