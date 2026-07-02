package com.toquete.boxbox.feature.standings.constructors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

internal class ConstructorStandingsViewModel(
    repository: ConstructorStandingsRepository
) : ViewModel() {

    val state = repository.getConstructorStandings()
        .map { ConstructorStandingsState(it.toImmutableList()) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ConstructorStandingsState()
        )
}
