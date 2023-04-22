package com.toquete.boxbox.database.model

data class FullDriverStandingEntity(
    val position: Int,
    val points: String,
    val wins: String,
    val driverId: String,
    val firstName: String,
    val lastName: String,
    val imageUrl: String,
    val flagUrl: String,
    val constructorId: String,
    val constructorName: String,
    val constructorImageUrl: String
)
