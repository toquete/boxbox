package com.toquete.boxbox.data.constructorcolors.model

import com.toquete.boxbox.core.database.model.ConstructorColorEntity
import com.toquete.boxbox.core.network.model.ConstructorColorResponse

fun ConstructorColorResponse.toEntity(): ConstructorColorEntity {
    return ConstructorColorEntity(
        id = id.orEmpty(),
        accentColor = accentColor.orEmpty(),
        backgroundColor = backgroundColor.orEmpty()
    )
}
