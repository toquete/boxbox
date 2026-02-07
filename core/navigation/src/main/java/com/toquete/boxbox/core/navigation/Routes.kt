package com.toquete.boxbox.core.navigation

import kotlinx.serialization.Serializable

@Serializable
object HomeRoute

@Serializable
object StandingsRoute

@Serializable
object RacesRoute

@Serializable
object SettingsRoute

@Serializable
data class RaceResultRoute(val round: Int, val race: String)
