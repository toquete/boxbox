package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.CountryEntity
import com.toquete.boxbox.core.network.model.CountryResponse

val countryEntities = listOf(
    CountryEntity(
        id = "NED",
        name = "Netherlands",
        nationality = "Dutch",
        flagUrl = "http://flag.com"
    ),
    CountryEntity(
        id = "AUT",
        name = "Austria",
        nationality = "Austrian",
        flagUrl = "http://flag.com"
    )
)

val countryResponses = listOf(
    CountryResponse(
        id = "NED",
        name = "Netherlands",
        nationality = "Dutch",
        flagUrl = "http://flag.com"
    ),
    CountryResponse(
        id = "AUT",
        name = "Austria",
        nationality = "Austrian",
        flagUrl = "http://flag.com"
    )
)
