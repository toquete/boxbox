package com.toquete.boxbox.data.driverimages.model

import com.toquete.boxbox.core.database.model.DriverImageEntity
import com.toquete.boxbox.core.network.model.DriverImageResponse

fun DriverImageResponse.toEntity(): DriverImageEntity {
    return DriverImageEntity(
        id = id.orEmpty(),
        imageUrl = imageUrl.orEmpty(),
        numberUrl = numberUrl.orEmpty()
    )
}
