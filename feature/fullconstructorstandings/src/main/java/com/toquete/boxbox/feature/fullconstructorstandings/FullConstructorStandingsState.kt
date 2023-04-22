package com.toquete.boxbox.feature.fullconstructorstandings

import com.toquete.boxbox.model.FullConstructorStanding

internal sealed interface FullConstructorStandingsState {
    object Loading : FullConstructorStandingsState
    data class Success(val data: List<FullConstructorStanding>) : FullConstructorStandingsState
    object Error : FullConstructorStandingsState
}
