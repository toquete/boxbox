package com.toquete.boxbox.data.fulldriverstandings.model

import com.toquete.boxbox.database.model.ConstructorEntity
import com.toquete.boxbox.network.model.ConstructorResponse

internal fun ConstructorResponse.toEntity(): ConstructorEntity {
    return ConstructorEntity(
        id = id,
        url = url,
        name = name,
        nationality = nationality
    )
}