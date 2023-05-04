package com.toquete.boxbox.data.fullconstructorstandings.model

import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.database.model.FullConstructorStandingEntity
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse

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
