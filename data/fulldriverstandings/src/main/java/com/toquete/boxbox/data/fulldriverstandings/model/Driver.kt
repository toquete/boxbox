package com.toquete.boxbox.data.fulldriverstandings.model

import com.toquete.boxbox.database.model.DriverEntity
import com.toquete.boxbox.network.model.DriverResponse

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