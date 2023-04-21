package com.toquete.boxbox.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toquete.boxbox.model.DriverStanding

@Entity(tableName = "driver_standings")
data class DriverStandingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val position: Int,
    val points: String,
    @ColumnInfo(name = "driver_id")
    val driverId: String,
    @ColumnInfo(name = "constructor_id")
    val constructorId: String
)

fun DriverStandingEntity.asDomain(): DriverStanding {
    return DriverStanding(
        position = position,
        points = points,
        driverId = driverId,
        constructorId = constructorId
    )
}

fun DriverStanding.asEntity(): DriverStandingEntity {
    return DriverStandingEntity(
        position = position,
        points = points,
        driverId = driverId,
        constructorId = constructorId
    )
}