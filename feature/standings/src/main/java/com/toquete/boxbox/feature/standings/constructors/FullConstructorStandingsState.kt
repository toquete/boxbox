package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.core.model.FullConstructorStanding

internal sealed interface FullConstructorStandingsState {
    object Loading : FullConstructorStandingsState
    data class Success(val constructorStandings: List<FullConstructorStanding>) : FullConstructorStandingsState
}