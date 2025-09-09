package com.toquete.boxbox.data.constructorimages.model

import com.toquete.boxbox.core.database.model.ConstructorImageEntity
import com.toquete.boxbox.core.network.model.ConstructorImageResponse

fun ConstructorImageResponse.toEntity(): ConstructorImageEntity {
    return ConstructorImageEntity(
        id = id.orEmpty(),
        imageUrl = imageUrl.orEmpty()
    )
}
