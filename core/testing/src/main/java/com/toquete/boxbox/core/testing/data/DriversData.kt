package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.DriverEntity
import com.toquete.boxbox.core.model.Driver

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

val driver = Driver(
    id = "max_verstappen",
    firstName = "Max",
    lastName = "Verstappen",
    imageUrl = "http://image.com",
    flagUrl = "http://flag.com"
)