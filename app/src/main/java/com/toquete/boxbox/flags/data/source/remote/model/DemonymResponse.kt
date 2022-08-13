package com.toquete.boxbox.flags.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DemonymResponse(
    @SerialName("eng")
    val englishDemonym: EnglishDemonymResponse
)

@Serializable
data class EnglishDemonymResponse(
    @SerialName("m")
    val masculine: String
)
