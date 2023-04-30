package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constructor_standings")
data class ConstructorStandingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val position: Int,
    val points: String,
    val wins: String,
    @ColumnInfo(name = "constructor_id")
    val constructorId: String
)
