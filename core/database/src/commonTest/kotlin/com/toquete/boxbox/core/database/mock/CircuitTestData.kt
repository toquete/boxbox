package com.toquete.boxbox.core.database.mock

import com.toquete.boxbox.core.database.model.CircuitEntity

val circuitEntities = listOf(
    CircuitEntity(
        id = "bahrain",
        circuitUrl = "http://en.wikipedia.org/wiki/Bahrain_International_Circuit",
        circuitName = "Bahrain International Circuit",
        latitude = "26.0325",
        longitude = "50.5106",
        locality = "Sakhir",
        country = "Bahrain"
    ),
    CircuitEntity(
        id = "americas",
        circuitUrl = "http://en.wikipedia.org/wiki/Circuit_of_the_Americas",
        circuitName = "Circuit of the Americas",
        latitude = "30.1328",
        longitude = "-97.6411",
        locality = "Austin",
        country = "USA"
    )
)
