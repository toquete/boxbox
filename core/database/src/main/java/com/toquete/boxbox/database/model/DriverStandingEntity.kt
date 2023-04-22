package com.toquete.boxbox.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "driver_standings")
data class DriverStandingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val position: Int,
    val points: String,
    val wins: String,
    @ColumnInfo(name = "driver_id")
    val driverId: String,
    @ColumnInfo(name = "constructor_id")
    val constructorId: String
)
