package com.toquete.boxbox.flags.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlagsResponse(
    @SerialName("png")
    val png: String,
    @SerialName("svg")
    val svg: String
)
