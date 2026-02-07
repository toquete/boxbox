package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.toquete.boxbox.core.navigation.RaceResultRoute
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RaceResultsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    getCurrentSeasonRaceResultsUseCase: GetCurrentSeasonRaceResultsUseCase,
    getCurrentSprintResultsUseCase: GetCurrentSeasonSprintResultsUseCase
) : ViewModel() {

    private val route = savedStateHandle.toRoute<RaceResultRoute>()
    private val round: Int = route.round
    private val raceName: String = route.race

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
