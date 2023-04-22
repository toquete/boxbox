package com.toquete.boxbox.feature.fullconstructorstandings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.fullconstructorstandings.GetFullConstructorStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class ConstructorStandingsViewModel @Inject constructor(
    getFullConstructorStandingsUseCase: GetFullConstructorStandingsUseCase
) : ViewModel() {

    val state = getFullConstructorStandingsUseCase()
        .map { ConstructorStandingsState.Success(it) }
        .onStart { ConstructorStandingsState.Loading }
        .catch { ConstructorStandingsState.Error }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = ConstructorStandingsState.Loading
        )
}