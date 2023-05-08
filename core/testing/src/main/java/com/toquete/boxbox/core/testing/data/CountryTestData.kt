package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.CountryEntity

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
