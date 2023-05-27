package com.toquete.boxbox.core.database.model

import androidx.room.Embedded
import androidx.room.Relation

data class DriverWithCountryEntity(
    @Embedded val driver: DriverEntity,
    @Relation(
        parentColumn = "nationality",
        entityColumn = "nationality",
    )
    val countryEntity: CountryEntity
)
