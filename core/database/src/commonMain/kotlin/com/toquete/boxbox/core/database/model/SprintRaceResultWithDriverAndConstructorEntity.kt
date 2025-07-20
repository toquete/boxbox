package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class SprintRaceResultWithDriverAndConstructorEntity(
    @Embedded val sprintRaceResult: SprintRaceResultEntity,
    @Relation(
        parentColumn = "driver_id",
        entityColumn = "id"
    )
    val driver: DriverEntity,
    @Relation(
        parentColumn = "constructor_id",
        entityColumn = "id"
    )
    val constructor: ConstructorEntity
)
