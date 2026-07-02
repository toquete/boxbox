package com.toquete.boxbox.feature.standings.drivers

import com.toquete.boxbox.core.model.DriverStanding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class DriverStandingsState(
    val standings: ImmutableList<DriverStanding> = persistentListOf()
)
