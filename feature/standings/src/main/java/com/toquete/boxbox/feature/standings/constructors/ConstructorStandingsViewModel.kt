package com.toquete.boxbox.feature.standings.constructors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.repository.ConstructorStandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ConstructorStandingsViewModel @Inject constructor(
    repository: ConstructorStandingsRepository
) : ViewModel() {

    val state = repository.getConstructorStandings()
        .map { ConstructorStandingsState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ConstructorStandingsState()
        )
}
