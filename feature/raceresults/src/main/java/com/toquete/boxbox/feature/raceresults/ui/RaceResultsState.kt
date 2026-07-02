package com.toquete.boxbox.feature.raceresults.ui

import com.toquete.boxbox.core.model.RaceResult
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class RaceResultsState(
    val raceName: String = "",
    val results: ImmutableList<RaceResult> = persistentListOf(),
    val sprintResults: ImmutableList<RaceResult> = persistentListOf()
)
