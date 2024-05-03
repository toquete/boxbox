package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.raceresults.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.feature.raceresults.navigation.ROUND_ARGUMENT
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RaceResultsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCurrentSeasonRaceResultsUseCase: GetCurrentSeasonRaceResultsUseCase
) : ViewModel() {

    private val round: Int = checkNotNull(savedStateHandle[ROUND_ARGUMENT])

    val state = getCurrentSeasonRaceResultsUseCase(round)
        .map { RaceResultsState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RaceResultsState()
        )
}
