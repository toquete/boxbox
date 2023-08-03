package com.toquete.boxbox.core.database.model

import androidx.room.Embedded

data class RaceWithCircuitEntity(
    @Embedded val race: RaceEntity,
    @Embedded val circuit: CircuitEntity,
    val flagUrl: String?,
    val circuitImageUrl: String?
)
