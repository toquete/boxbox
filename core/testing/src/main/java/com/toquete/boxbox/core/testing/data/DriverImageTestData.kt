package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.DriverImageEntity
import com.toquete.boxbox.core.network.model.DriverImageResponse

val driverImageEntities = listOf(
    DriverImageEntity(
        id = "max_verstappen",
        imageUrl = "http://image.com"
    )
)

val driverImageResponses = listOf(
    DriverImageResponse(
        id = "max_verstappen",
        imageUrl = "http://image.com"
    )
)
