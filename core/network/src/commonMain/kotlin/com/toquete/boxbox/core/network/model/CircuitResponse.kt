package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CircuitResponse(
    @SerialName("circuitId")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("circuitName")
    val name: String,
    @SerialName("Location")
    val location: LocationResponse
)
