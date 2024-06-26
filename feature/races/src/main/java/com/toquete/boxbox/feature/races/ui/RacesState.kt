package com.toquete.boxbox.feature.races.ui

import com.toquete.boxbox.core.model.Race

internal data class RacesState(
    val upcomingRaces: List<Race> = emptyList(),
    val pastRaces: List<Race> = emptyList()
)
