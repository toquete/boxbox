package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class RaceWithCircuitEntity(
    @Embedded val race: RaceEntity,
    @Relation(
        entity = CircuitEntity::class,
        parentColumn = "circuit_id",
        entityColumn = "id"
    )
    val circuit: CircuitEntity
)
