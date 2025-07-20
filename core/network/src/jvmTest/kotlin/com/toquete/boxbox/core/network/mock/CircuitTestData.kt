package com.toquete.boxbox.core.network.mock

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
