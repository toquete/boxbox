package com.toquete.boxbox.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "circuits")
data class CircuitEntity(
    @PrimaryKey
    val id: String,
    val circuitUrl: String,
    val circuitName: String,
    val latitude: String,
    val longitude: String,
    val locality: String,
    val country: String
)
