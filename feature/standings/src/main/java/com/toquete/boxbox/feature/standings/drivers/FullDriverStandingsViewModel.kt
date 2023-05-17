package com.toquete.boxbox.feature.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.data.fulldriverstandings.repository.FullDriverStandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class FullDriverStandingsViewModel @Inject constructor(
    repository: FullDriverStandingsRepository
) : ViewModel() {

    val state = repository.getFullDriverStandings()
        .map { FullDriversStandingsState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FullDriversStandingsState.Loading
        )
}
