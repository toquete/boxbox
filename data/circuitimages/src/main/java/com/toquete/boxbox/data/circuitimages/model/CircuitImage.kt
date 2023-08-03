package com.toquete.boxbox.data.circuitimages.model

import com.toquete.boxbox.core.database.model.CircuitImageEntity
import com.toquete.boxbox.core.network.model.CircuitImageResponse

internal fun CircuitImageResponse.toEntity(): CircuitImageEntity {
    return CircuitImageEntity(
        id = id.orEmpty(),
        imageUrl = imageUrl.orEmpty()
    )
}
