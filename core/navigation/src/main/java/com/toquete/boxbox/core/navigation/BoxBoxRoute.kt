package com.toquete.boxbox.core.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed interface BoxBoxRoute {
    @Serializable
    data object Home : BoxBoxRoute

    @Serializable
    data object Standings : BoxBoxRoute

    @Serializable
    data object Races : BoxBoxRoute

    @Serializable
    data class RaceResult(val round: Int, val race: String) : BoxBoxRoute

    @Serializable
    data object Settings : BoxBoxRoute
}
