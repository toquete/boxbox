package com.toquete.boxbox.data.driverimages.mock

import com.toquete.boxbox.core.database.model.DriverImageEntity
import com.toquete.boxbox.core.network.model.DriverImageResponse

val driverImageEntities = listOf(
    DriverImageEntity(
        id = "max_verstappen",
        imageUrl = "http://image.com",
        numberUrl = "http://number.com"
    )
)

val driverImageResponses = listOf(
    DriverImageResponse(
        id = "max_verstappen",
        imageUrl = "http://image.com",
        numberUrl = "http://number.com"
    )
)
