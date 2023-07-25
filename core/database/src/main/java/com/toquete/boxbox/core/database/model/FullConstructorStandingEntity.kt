package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class FullConstructorStandingEntity(
    @Embedded val standing: ConstructorStandingEntity,
    @Relation(
        entity = ConstructorEntity::class,
        parentColumn = "constructor_id",
        entityColumn = "id"
    )
    val constructorWithCountryFlag: ConstructorWithCountryFlagEntity,
    @Relation(
        entity = ConstructorImageEntity::class,
        parentColumn = "constructor_id",
        entityColumn = "id",
        projection = ["image_url"]
    )
    val constructorImageUrl: String?
)
