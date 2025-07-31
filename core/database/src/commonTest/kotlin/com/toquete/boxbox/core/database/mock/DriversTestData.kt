package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.DriverEntity

val driverEntities = listOf(
    DriverEntity(
        id = "max_verstappen",
        number = "33",
        code = "VER",
        url = "http://en.wikipedia.org/wiki/Max_Verstappen",
        firstName = "Max",
        lastName = "Verstappen",
        dateOfBirth = "1997-09-30",
        nationality = "Dutch"
    )
)
