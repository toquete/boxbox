package com.toquete.boxbox.feature.constructorstandings

import com.toquete.boxbox.model.ConstructorStanding

internal sealed interface ConstructorStandingsState {
    object Loading : ConstructorStandingsState
    data class Success(val data: List<ConstructorStanding>) : ConstructorStandingsState
    object Error : ConstructorStandingsState
}
