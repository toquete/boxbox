package com.toquete.boxbox.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.toquete.boxbox.model.DriverStanding

@Entity(tableName = "driver_standings")
data class DriverStandingEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int? = null,
    val position: Int,
    val name: String,
    val lastName: String,
    val nationality: String,
    val car: String,
    val points: String
)

fun DriverStandingEntity.asDomain(): DriverStanding {
    return DriverStanding(
        position = position.toString(),
        name = name,
        lastName = lastName,
        nationality = nationality,
        car = car,
        points = points
    )
}

fun DriverStanding.asEntity(): DriverStandingEntity {
    return DriverStandingEntity(
        position = position.toInt(),
        name = name,
        lastName = lastName,
        nationality = nationality,
        car = car,
        points = points
    )
}