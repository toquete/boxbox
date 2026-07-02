package com.toquete.boxbox.feature.raceresults.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.toquete.boxbox.core.navigation.BoxBoxRoute
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonRaceResultsUseCase
import com.toquete.boxbox.domain.usecase.GetCurrentSeasonSprintResultsUseCase
import kotlinx.collections.immutable.toImmutableList
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

internal class RaceResultsViewModel(
    savedStateHandle: SavedStateHandle,
    getCurrentSeasonRaceResultsUseCase: GetCurrentSeasonRaceResultsUseCase,
    getCurrentSprintResultsUseCase: GetCurrentSeasonSprintResultsUseCase
) : ViewModel() {

    private val route: BoxBoxRoute.RaceResult = checkNotNull(savedStateHandle.toRoute())

    val state = combine(
        getCurrentSeasonRaceResultsUseCase(route.round),
        getCurrentSprintResultsUseCase(route.round)
    ) { raceResults, sprintResults ->
        RaceResultsState(route.race, raceResults.toImmutableList(), sprintResults.toImmutableList())
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RaceResultsState()
    )
}
