package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.CircuitEntity
import com.toquete.boxbox.core.network.model.CircuitResponse
import com.toquete.boxbox.core.network.model.LocationResponse

val circuitResponse = CircuitResponse(
    id = "bahrain",
    url = "http://en.wikipedia.org/wiki/Bahrain_International_Circuit",
    name = "Bahrain International Circuit",
    location = LocationResponse(
        latitude = "26.0325",
        longitude = "50.5106",
        locality = "Sakhir",
        country = "Bahrain"
    )
)

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
