package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorImageResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("image_url")
    val imageUrl: String? = null
)
