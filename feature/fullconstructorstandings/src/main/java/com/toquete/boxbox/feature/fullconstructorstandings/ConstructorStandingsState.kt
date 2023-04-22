package com.toquete.boxbox.feature.fullconstructorstandings

import com.toquete.boxbox.model.FullConstructorStanding

internal sealed interface ConstructorStandingsState {
    object Loading : ConstructorStandingsState
    data class Success(val data: List<FullConstructorStanding>) : ConstructorStandingsState
    object Error : ConstructorStandingsState
}
