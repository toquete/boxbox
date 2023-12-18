package com.toquete.boxbox.core.model

data class Driver(
    val id: String,
    val firstName: String,
    val lastName: String,
    val imageUrl: String? = null,
    val flagUrl: String? = null,
    val numberUrl: String? = null
)
