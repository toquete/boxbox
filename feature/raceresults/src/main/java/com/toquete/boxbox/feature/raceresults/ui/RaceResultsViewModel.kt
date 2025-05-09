package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import com.toquete.boxbox.feature.raceresults.navigation.RACE_ARGUMENT
import com.toquete.boxbox.feature.raceresults.navigation.ROUND_ARGUMENT
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

internal class RaceResultsViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentSeasonRaceResultsUseCase: GetCurrentSeasonRaceResultsUseCase,
    getCurrentSprintResultsUseCase: GetCurrentSeasonSprintResultsUseCase
) : ViewModel() {

    private val round: Int = checkNotNull(savedStateHandle[ROUND_ARGUMENT])
    private val raceName: String = checkNotNull(savedStateHandle[RACE_ARGUMENT])

    val state = combine(
        getCurrentSeasonRaceResultsUseCase(round),
        getCurrentSprintResultsUseCase(round)
    ) { raceResults, sprintResults ->
        RaceResultsState(raceName, raceResults, sprintResults)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RaceResultsState()
    )
}
