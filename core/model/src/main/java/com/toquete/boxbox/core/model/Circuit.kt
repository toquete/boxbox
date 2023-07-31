package com.toquete.boxbox.core.model

data class Circuit(
    val id: String,
    val url: String,
    val name: String,
    val location: Location,
    val locality: String,
    val country: String,
    val flagUrl: String?
)
