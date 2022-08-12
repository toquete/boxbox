package com.toquete.boxbox.standings.driver.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverResponse(
    @SerialName("givenName")
    val givenName: String,
    @SerialName("familyName")
    val familyName: String,
    @SerialName("nationality")
    val nationality: String
)
