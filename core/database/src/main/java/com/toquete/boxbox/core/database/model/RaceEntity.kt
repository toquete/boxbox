package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(tableName = "races", primaryKeys = ["season", "round"])
data class RaceEntity(
    val season: String,
    val round: Int,
    val raceUrl: String,
    val raceName: String,
    val date: String,
    val time: String,
    @ColumnInfo(name = "circuit_id")
    val circuitId: String,
    @ColumnInfo(name = "first_practice_date")
    val firstPracticeDate: String,
    @ColumnInfo(name = "first_practice_time")
    val firstPracticeTime: String,
    @ColumnInfo(name = "second_practice_date")
    val secondPracticeDate: String,
    @ColumnInfo(name = "second_practice_time")
    val secondPracticeTime: String,
    @ColumnInfo(name = "third_practice_date")
    val thirdPracticeDate: String?,
    @ColumnInfo(name = "third_practice_time")
    val thirdPracticeTime: String?,
    @ColumnInfo(name = "qualifying_date")
    val qualifyingDate: String,
    @ColumnInfo(name = "qualifying_time")
    val qualifyingTime: String,
    @ColumnInfo(name = "sprint_date")
    val sprintDate: String?,
    @ColumnInfo(name = "sprint_time")
    val sprintTime: String?
)
