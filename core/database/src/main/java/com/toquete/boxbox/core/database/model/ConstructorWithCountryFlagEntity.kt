package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class ConstructorWithCountryFlagEntity(
    @Embedded val constructor: ConstructorEntity,
    @Relation(
        parentColumn = "nationality",
        entityColumn = "nationality",
        entity = CountryEntity::class,
        projection = ["flag_url"]
    )
    val flagUrl: String
)
