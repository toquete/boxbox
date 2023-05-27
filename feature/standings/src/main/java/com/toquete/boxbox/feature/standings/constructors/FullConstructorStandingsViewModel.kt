package com.toquete.boxbox.feature.standings.constructors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class FullConstructorStandingsViewModel @Inject constructor(
    repository: ConstructorStandingsRepository
) : ViewModel() {

    val state = repository.getConstructorStandings()
        .map { FullConstructorStandingsState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FullConstructorStandingsState.Loading
        )
}
