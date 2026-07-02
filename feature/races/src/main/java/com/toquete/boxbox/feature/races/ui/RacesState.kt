package com.toquete.boxbox.feature.races.ui

import com.toquete.boxbox.core.model.Race
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class RacesState(
    val upcomingRaces: ImmutableList<Race> = persistentListOf(),
    val pastRaces: ImmutableList<Race> = persistentListOf()
)
