package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constructors_colors")
data class ConstructorColorEntity(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "accent_color")
    val accentColor: String,
    @ColumnInfo(name = "background_color")
    val backgroundColor: String
)
