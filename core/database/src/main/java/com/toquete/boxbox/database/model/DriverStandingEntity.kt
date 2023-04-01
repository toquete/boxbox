package com.toquete.boxbox.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_standings")
data class DriverStandingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val position: String,
    val name: String,
    val lastName: String,
    val nationality: String,
    val car: String,
    val points: String
)