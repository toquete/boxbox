package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class FullDriverStandingEntity(
    @Embedded val standing: DriverStandingEntity,
    @Relation(
        entity = DriverEntity::class,
        parentColumn = "driver_id",
        entityColumn = "id"
    )
    val driverWithCountryFlag: DriverWithCountryFlagEntity,
    @Relation(
        parentColumn = "constructor_id",
        entityColumn = "id"
    )
    val constructor: ConstructorEntity,
    @Relation(
        entity = DriverImageEntity::class,
        parentColumn = "driver_id",
        entityColumn = "id",
        projection = ["image_url"]
    )
    val driverImageUrl: String?,
    @Relation(
        entity = ConstructorImageEntity::class,
        parentColumn = "constructor_id",
        entityColumn = "id",
        projection = ["image_url"]
    )
    val constructorImageUrl: String
)
