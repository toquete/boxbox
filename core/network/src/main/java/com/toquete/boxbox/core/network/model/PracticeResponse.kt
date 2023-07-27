package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PracticeResponse(
    @SerialName("date")
    val date: String,
    @SerialName("time")
    val time: String
)
