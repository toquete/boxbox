package com.toquete.boxbox.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "circuits")
data class CircuitEntity(
    @PrimaryKey
    val id: String,
    val url: String,
    val name: String,
    val latitude: String,
    val longitude: String,
    val locality: String,
    val country: String
)
