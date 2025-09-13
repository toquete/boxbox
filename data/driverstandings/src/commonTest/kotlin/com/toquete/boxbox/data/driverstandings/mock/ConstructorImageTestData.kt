package com.toquete.boxbox.data.driverstandings.mock

import com.toquete.boxbox.core.database.model.ConstructorImageEntity
import com.toquete.boxbox.core.network.model.ConstructorImageResponse

val constructorImageEntities = listOf(
    ConstructorImageEntity(
        id = "red_bull",
        imageUrl = "http://constructor.com"
    )
)

val constructorImageResponses = listOf(
    ConstructorImageResponse(
        id = "red_bull",
        imageUrl = "http://constructor.com"
    )
)
