package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RaceTableResponse(
    @SerialName("season")
    val season: String,
    @SerialName("Races")
    val races: List<RaceResponse>
)
