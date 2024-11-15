package com.toquete.boxbox.feature.raceresults.ui

import com.toquete.boxbox.core.model.RaceResult

internal data class RaceResultsState(
    val raceName: String = "",
    val results: List<RaceResult> = emptyList(),
    val sprintResults: List<RaceResult> = emptyList()
)
