package com.toquete.boxbox

import com.toquete.boxbox.model.FullConstructorStanding
import com.toquete.boxbox.model.FullDriverStanding

sealed interface MainState {
    object Loading : MainState
    data class Success(
        val drivers: List<FullDriverStanding>,
        val constructors: List<FullConstructorStanding>
    ) : MainState
}