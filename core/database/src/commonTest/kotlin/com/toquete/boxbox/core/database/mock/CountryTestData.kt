package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.CountryEntity

val countryEntities = listOf(
    CountryEntity(
        id = "NED",
        name = "Netherlands",
        nationality = "Dutch",
        flagUrl = "http://flag.com",
        alternativeId = null
    ),
    CountryEntity(
        id = "AUT",
        name = "Austria",
        nationality = "Austrian",
        flagUrl = "http://flag.com",
        alternativeId = null
    ),
    CountryEntity(
        id = "USA",
        name = "United States of America",
        nationality = "American",
        flagUrl = "http://america.com",
        alternativeId = null
    ),
    CountryEntity(
        id = "BAH",
        name = "Bahrain",
        nationality = "Bahraini",
        flagUrl = "http://bahrain.com",
        alternativeId = null
    )
)
