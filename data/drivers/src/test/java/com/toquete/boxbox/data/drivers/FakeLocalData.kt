package com.toquete.boxbox.data.drivers

import com.toquete.boxbox.database.model.DriverEntity

object FakeLocalData {

    val drivers = listOf(
        DriverEntity(
            id = "max_versappen",
            number = "33",
            code = "MAX",
            url = "http://verstappen.com",
            firstName = "Max",
            lastName = "Verstappen",
            dateOfBirth = "1997-08-09",
            nationality = "Dutch"
        )
    )
}