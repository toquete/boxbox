package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.ConstructorStanding

internal sealed interface FullConstructorStandingsState {
    object Loading : FullConstructorStandingsState
    data class Success(val constructorStandings: List<ConstructorStanding>) : FullConstructorStandingsState
}
