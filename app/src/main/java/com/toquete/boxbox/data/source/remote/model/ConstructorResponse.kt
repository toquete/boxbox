package com.toquete.boxbox.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorResponse(
    @SerialName("name")
    val name: String
)
