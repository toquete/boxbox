package com.toquete.boxbox.feature.races.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.races.usecase.GetCurrentSeasonRacesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class RacesViewModel @Inject constructor(
    getCurrentSeasonRacesUseCase: GetCurrentSeasonRacesUseCase
) : ViewModel() {

    val state = getCurrentSeasonRacesUseCase()
        .map { RacesState(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = RacesState()
        )
}
