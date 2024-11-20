package com.toquete.boxbox.feature.races.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.usecase.GetPastRacesInCurrentSeasonUseCase
import com.toquete.boxbox.domain.usecase.GetUpcomingRacesInCurrentSeasonUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RacesViewModel @Inject constructor(
    getUpcomingRacesUseCase: GetUpcomingRacesInCurrentSeasonUseCase,
    getPastRacesUseCase: GetPastRacesInCurrentSeasonUseCase
) : ViewModel() {

    val state = combine(
        getUpcomingRacesUseCase(),
        getPastRacesUseCase()
    ) { upcomingRaces, pastRaces ->
        RacesState(
            upcomingRaces,
            pastRaces
        )
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = RacesState()
    )
}
