package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "countries")
data class CountryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val nationality: String,
    @ColumnInfo(name = "flag_url")
    val flagUrl: String,
    @ColumnInfo(name = "alternative_id")
    val alternativeId: String?
)
