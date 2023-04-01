package com.toquete.boxox.data.driverstandings.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class StandingsTableResponse<T>(
    @SerialName("StandingsTable")
    val standingTable: T
)

@Serializable
internal data class StandingsLists<T>(
    @SerialName("StandingsLists")
    val standingsLists: List<T>
)
