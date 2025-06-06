package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TimeResponse(
    @SerialName("millis")
    val millis: String,
    @SerialName("time")
    val time: String
)
