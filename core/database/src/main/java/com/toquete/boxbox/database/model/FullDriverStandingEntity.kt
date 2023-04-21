package com.toquete.boxbox.database.model

import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.Driver
import com.toquete.boxbox.model.FullDriverStanding

data class FullDriverStandingEntity(
    val position: Int,
    val points: String,
    val driverId: String,
    val firstName: String,
    val lastName: String,
    val imageUrl: String,
    val flagUrl: String,
    val constructorId: String,
    val constructorName: String,
    val constructorImageUrl: String
)

fun FullDriverStandingEntity.asFullDomain(): FullDriverStanding {
    return FullDriverStanding(
        position = position,
        points = points,
        driver = Driver(
            id = driverId,
            firstName = firstName,
            lastName = lastName,
            imageUrl = imageUrl,
            flagUrl = flagUrl
        ),
        constructor = Constructor(
            id = constructorId,
            name = constructorName,
            imageUrl = constructorImageUrl
        )
    )
}