package com.toquete.boxbox.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsTableResponse<T>(
    @SerialName("StandingsTable")
    val standingTable: T
)

@Serializable
data class StandingsLists<T>(
    @SerialName("StandingsLists")
    val standingsLists: List<T>
)
