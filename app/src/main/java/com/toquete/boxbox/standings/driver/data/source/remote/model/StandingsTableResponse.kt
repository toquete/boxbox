package com.toquete.boxbox.standings.driver.data.source.remote.model

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
