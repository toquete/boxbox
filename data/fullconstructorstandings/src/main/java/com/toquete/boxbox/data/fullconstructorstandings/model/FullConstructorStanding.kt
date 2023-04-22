package com.toquete.boxbox.data.fullconstructorstandings.model

import com.toquete.boxbox.database.model.ConstructorStandingEntity
import com.toquete.boxbox.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.FullConstructorStanding
import com.toquete.boxbox.network.model.ConstructorStandingResponse

internal fun ConstructorStandingResponse.toEntity(): ConstructorStandingEntity {
    return ConstructorStandingEntity(
        position = position.toInt(),
        points = points,
        wins = wins,
        constructorId = constructor.id
    )
}

internal fun FullConstructorStandingEntity.toDomain(): FullConstructorStanding {
    return FullConstructorStanding(
        position = position,
        points = points,
        wins = wins,
        constructor = Constructor(
            id = constructorId,
            name = constructorName,
            imageUrl = imageUrl
        )
    )
}