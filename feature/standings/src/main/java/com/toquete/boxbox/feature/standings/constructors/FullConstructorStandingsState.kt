package com.toquete.boxbox.feature.standings.constructors

import com.toquete.boxbox.model.FullConstructorStanding

internal sealed interface FullConstructorStandingsState {
    data class Success(val data: List<FullConstructorStanding>) : FullConstructorStandingsState
}
