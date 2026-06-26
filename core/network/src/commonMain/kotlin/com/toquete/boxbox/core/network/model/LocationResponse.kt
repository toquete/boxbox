package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class LocationResponse(
    @SerialName("lat")
    val latitude: String,
    @SerialName("long")
    val longitude: String,
    @SerialName("locality")
    val locality: String,
    @SerialName("country")
    val country: String
)
