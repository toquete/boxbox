package com.toquete.boxbox.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "constructors_images")
data class ConstructorImage(
    @PrimaryKey
    val id: String,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)
