package com.toquete.boxbox.standings.driver.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StandingsTableResponse<T>(
    @SerialName("StandingTable")
    val standingTable: T
)

@Serializable
data class StandingsLists<T>(
    @SerialName("StandingLists")
    val standingsLists: List<T>
)
