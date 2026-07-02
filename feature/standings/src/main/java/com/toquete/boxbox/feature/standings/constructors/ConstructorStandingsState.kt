package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.ConstructorStanding
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

internal data class ConstructorStandingsState(
    val standings: ImmutableList<ConstructorStanding> = persistentListOf()
)
