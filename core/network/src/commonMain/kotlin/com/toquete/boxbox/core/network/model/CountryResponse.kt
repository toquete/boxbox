package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CountryResponse(
    @SerialName("id")
    val id: String? = null,
    @SerialName("name")
    val name: String? = null,
    @SerialName("nationality")
    val nationality: String? = null,
    @SerialName("flag_url")
    val flagUrl: String? = null,
    @SerialName("alternative_id")
    val alternativeId: String? = null
)
