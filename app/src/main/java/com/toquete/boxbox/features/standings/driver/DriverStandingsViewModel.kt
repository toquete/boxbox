package com.toquete.boxbox.features.standings.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.usecase.GetDriverStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DriverStandingsViewModel @Inject constructor(
    private val getDriverStandingsUseCase: GetDriverStandingsUseCase
) : ViewModel() {

    private val _newState = MutableStateFlow<DriverStandingsState>(DriverStandingsState.Loading)
    val newState = _newState.asStateFlow()

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            _newState.update { DriverStandingsState.Loading }
            runCatching {
                getDriverStandingsUseCase()
            }.onSuccess { standings ->
                _newState.update { DriverStandingsState.Success(standings) }
            }
        }
    }
}