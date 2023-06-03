package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.ConstructorStanding

internal data class ConstructorStandingsState(
    val standings: List<ConstructorStanding> = emptyList()
)
