package com.toquete.boxbox.feature.driverstandings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.fulldriverstandings.GetFullDriverStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class FullDriverStandingsViewModel @Inject constructor(
    private val getFullDriverStandingsUseCase: GetFullDriverStandingsUseCase
) : ViewModel() {

    private val _newState = MutableStateFlow<FullDriverStandingsState>(FullDriverStandingsState.Loading)
    val newState = _newState.asStateFlow()

    init {
        getDriverStandings()
    }

    private fun getDriverStandings() {
        viewModelScope.launch {
            _newState.update { FullDriverStandingsState.Loading }
            runCatching {
                getFullDriverStandingsUseCase()
            }.onSuccess { standings ->
                _newState.update { FullDriverStandingsState.Success(standings) }
            }
        }
    }
}