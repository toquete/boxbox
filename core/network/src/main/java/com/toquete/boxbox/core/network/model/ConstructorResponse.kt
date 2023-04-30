package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorResponse(
    @SerialName("constructorId")
    val id: String,
    @SerialName("url")
    val url: String,
    @SerialName("name")
    val name: String,
    @SerialName("nationality")
    val nationality: String
)
