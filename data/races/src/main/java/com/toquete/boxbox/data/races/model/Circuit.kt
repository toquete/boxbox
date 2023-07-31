package com.toquete.boxbox.data.races.model

import com.toquete.boxbox.core.database.model.CircuitEntity
import com.toquete.boxbox.core.network.model.CircuitResponse

internal fun CircuitResponse.toEntity(): CircuitEntity {
    return CircuitEntity(
        id = id,
        circuitUrl = url,
        circuitName = name,
        latitude = location.latitude,
        longitude = location.longitude,
        locality = location.locality,
        country = location.country
    )
}
