package com.toquete.boxbox.data.circuitimages.mock

import com.toquete.boxbox.core.database.model.CircuitImageEntity
import com.toquete.boxbox.core.network.model.CircuitImageResponse

val circuitImageResponses = listOf(
    CircuitImageResponse(
        id = "bahrain",
        imageUrl = "http://image.com"
    ),
    CircuitImageResponse(
        id = "americas",
        imageUrl = "http://image.com"
    )
)

val circuitImageEntities = listOf(
    CircuitImageEntity(
        id = "bahrain",
        imageUrl = "http://image.com"
    ),
    CircuitImageEntity(
        id = "americas",
        imageUrl = "http://image.com"
    )
)
