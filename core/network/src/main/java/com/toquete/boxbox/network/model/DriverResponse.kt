package com.toquete.boxbox.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverResponse(
    @SerialName("driverId")
    val id: String,
    @SerialName("permanentNumber")
    val number: String,
    @SerialName("code")
    val code: String,
    @SerialName("url")
    val url: String,
    @SerialName("givenName")
    val givenName: String,
    @SerialName("familyName")
    val familyName: String,
    @SerialName("dateOfBirth")
    val dateOfBirth: String,
    @SerialName("nationality")
    val nationality: String
)
