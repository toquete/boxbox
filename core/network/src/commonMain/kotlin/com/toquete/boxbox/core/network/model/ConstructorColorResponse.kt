package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorColorResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("accent_color")
    val accentColor: String? = null,
    @SerialName("background_color")
    val backgroundColor: String? = null
)
