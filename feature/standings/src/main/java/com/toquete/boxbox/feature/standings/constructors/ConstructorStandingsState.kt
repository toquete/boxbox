package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.ConstructorStanding

internal sealed interface ConstructorStandingsState {
    object Loading : ConstructorStandingsState
    data class Success(val constructorStandings: List<ConstructorStanding>) : ConstructorStandingsState
}
