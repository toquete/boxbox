package com.toquete.boxbox.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DriverResponse(
    @SerialName("driver_id")
    val id: String,
    @SerialName("permanent_number")
    val number: String,
    @SerialName("code")
    val code: String,
    @SerialName("givenName")
    val givenName: String,
    @SerialName("familyName")
    val familyName: String,
    @SerialName("dateOfBirth")
    val dateOfBirth: String,
    @SerialName("nationality")
    val nationality: String
)
