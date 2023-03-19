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

    var state by mutableStateOf(DriverStandingsState())
        private set

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            state = state.copy(isLoading = true)
            runCatching {
                getDriverStandingsUseCase()
            }.onSuccess { standings ->
                state = state.copy(
                    standings = standings,
                    isLoading = false
                )
            }
        }
    }
}