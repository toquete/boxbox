package com.toquete.boxbox.features.standings.driver

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.usecase.GetDriverStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverStandingsViewModel @Inject constructor(
    private val getDriverStandingsUseCase: GetDriverStandingsUseCase
) : ViewModel() {

    var state by mutableStateOf<DriverStandingsState>(DriverStandingsState.Loading)
        private set

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            state = DriverStandingsState.Loading
            runCatching {
                getDriverStandingsUseCase()
            }.onSuccess { standings ->
                state = DriverStandingsState.Success(standings)
            }
        }
    }
}