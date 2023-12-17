package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class DriverWithDriverImageEntity(
    @Embedded val driver: DriverEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        entity = DriverImageEntity::class
    )
    val driverImage: DriverImageEntity?
)
