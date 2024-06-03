package com.toquete.boxbox.feature.races.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.races.usecase.GetCurrentSeasonRacesUseCase
import com.toquete.boxbox.domain.races.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.races.usecase.GetUpcomingRacesInCurrentSeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RacesViewModel @Inject constructor(
    getCurrentSeasonRacesUseCase: GetCurrentSeasonRacesUseCase,
    getUpcomingRacesUseCase: GetUpcomingRacesInCurrentSeasonUseCase,
    getPastRacesUseCase: GetPastRacesInCurrentSeasonUseCase
) : ViewModel() {

    val state = combine(
        getCurrentSeasonRacesUseCase(),
        getUpcomingRacesUseCase(),
        getPastRacesUseCase()
    ) { currentSeasonRaces, upcomingRaces, pastRaces ->
        RacesState(
            currentSeasonRaces,
            upcomingRaces,
            pastRaces
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RacesState()
    )
}
