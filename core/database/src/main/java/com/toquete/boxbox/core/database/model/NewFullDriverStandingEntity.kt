package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class NewFullDriverStandingEntity(
    @Embedded val standingEntity: DriverStandingEntity,
    @Relation(
        parentColumn = "driver_id",
        entityColumn = "id"
    )
    val driver: DriverEntity,
    @Relation(
        parentColumn = "constructor_id",
        entityColumn = "id"
    )
    val constructor: ConstructorEntity,
    @Relation(
        parentColumn = "driver_id",
        entityColumn = "id"
    )
    val driverImage: DriverImageEntity,
    @Relation(
        parentColumn = "constructor_id",
        entityColumn = "id"
    )
    val constructorImage: ConstructorImageEntity,
    @Relation(
        entity = DriverEntity::class,
        parentColumn = "driver_id",
        entityColumn = "id"
    )
    val driverWithCountryEntity: DriverWithCountryEntity
)
