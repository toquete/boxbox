package com.toquete.boxbox.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorResponse(
    @SerialName("name")
    val name: String
)
