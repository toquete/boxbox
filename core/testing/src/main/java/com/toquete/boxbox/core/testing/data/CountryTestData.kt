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
    ),
    CountryEntity(
        id = "USA",
        name = "United States of America",
        nationality = "American",
        flagUrl = "http://america.com"
    ),
    CountryEntity(
        id = "BAH",
        name = "Bahrain",
        nationality = "Bahraini",
        flagUrl = "http://bahrain.com"
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
    ),
    CountryResponse(
        id = "USA",
        name = "United States of America",
        nationality = "American",
        flagUrl = "http://america.com"
    ),
    CountryResponse(
        id = "BAH",
        name = "Bahrain",
        nationality = "Bahraini",
        flagUrl = "http://bahrain.com"
    )
)
