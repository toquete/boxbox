package com.toquete.boxbox.domain.mock

import com.toquete.boxbox.core.model.Circuit
import com.toquete.boxbox.core.model.Location

val circuits = listOf(
    Circuit(
        id = "bahrain",
        url = "http://en.wikipedia.org/wiki/Bahrain_International_Circuit",
        name = "Bahrain International Circuit",
        location = Location(
            latitude = "26.0325",
            longitude = "50.5106"
        ),
        locality = "Sakhir",
        country = "Bahrain",
        flagUrl = "http://bahrain.com",
        imageUrl = "http://image.com"
    ),
    Circuit(
        id = "americas",
        url = "http://en.wikipedia.org/wiki/Circuit_of_the_Americas",
        name = "Circuit of the Americas",
        location = Location(
            latitude = "30.1328",
            longitude = "-97.6411"
        ),
        locality = "Austin",
        country = "USA",
        flagUrl = "http://america.com",
        imageUrl = "http://image.com"
    )
)
