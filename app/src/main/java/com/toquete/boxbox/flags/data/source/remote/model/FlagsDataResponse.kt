package com.toquete.boxbox.flags.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FlagsDataResponse(
    @SerialName("flags")
    val flagsResponse: FlagsResponse,
    @SerialName("demonyms")
    val demonym: DemonymResponse
)
