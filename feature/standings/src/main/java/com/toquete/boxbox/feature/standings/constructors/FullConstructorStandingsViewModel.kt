package com.toquete.boxbox.feature.standings.constructors

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.fullconstructorstandings.GetFullConstructorStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class FullConstructorStandingsViewModel @Inject constructor(
    getFullConstructorStandingsUseCase: GetFullConstructorStandingsUseCase
) : ViewModel() {

    val state = getFullConstructorStandingsUseCase()
        .map { FullConstructorStandingsState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FullConstructorStandingsState.Loading
        )
}