package com.toquete.boxbox

import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.model.FullDriverStanding

sealed interface MainState {
    object Loading : MainState
    data class Success(
        val drivers: List<FullDriverStanding>,
        val constructors: List<FullConstructorStanding>
    ) : MainState
}