package com.toquete.boxbox.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constructors")
data class ConstructorEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val name: String,
    val nationality: String
)
