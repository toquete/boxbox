package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "sprint_race_results", primaryKeys = ["season", "round", "position"])
data class SprintRaceResultEntity(
    val season: String,
    val round: Int,
    val position: Int,
    val points: Int,
    @ColumnInfo(name = "driver_id")
    val driverId: String,
    @ColumnInfo(name = "constructor_id")
    val constructorId: String,
    @ColumnInfo(name = "grid_position")
    val gridPosition: Int,
    val laps: String,
    val status: String,
    val time: String?
)
