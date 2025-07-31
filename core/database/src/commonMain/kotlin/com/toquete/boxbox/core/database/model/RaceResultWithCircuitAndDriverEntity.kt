package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Embedded

data class RaceResultWithCircuitAndDriverEntity(
    @Embedded val raceResult: RaceResultEntity,
    val country: String,
    val circuitName: String,
    @ColumnInfo("code")
    val driverCode: String,
    @ColumnInfo("image_url")
    val driverImageUrl: String?
)
