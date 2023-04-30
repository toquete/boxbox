package com.toquete.boxbox.data.fulldriverstandings.model

import com.toquete.boxbox.core.database.model.ConstructorEntity
import com.toquete.boxbox.core.network.model.ConstructorResponse

internal fun ConstructorResponse.toEntity(): ConstructorEntity {
    return ConstructorEntity(
        id = id,
        url = url,
        name = name,
        nationality = nationality
    )
}