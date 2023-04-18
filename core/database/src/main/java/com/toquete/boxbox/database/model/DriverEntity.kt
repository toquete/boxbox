package com.toquete.boxbox.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "drivers")
data class DriverEntity(
    @PrimaryKey
    val id: String,
    val number: String,
    val code: String,
    val url: String,
    @ColumnInfo(name = "first_name")
    val firstName: String,
    @ColumnInfo(name = "last_name")
    val lastName: String,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: String,
    val nationality: String
)
