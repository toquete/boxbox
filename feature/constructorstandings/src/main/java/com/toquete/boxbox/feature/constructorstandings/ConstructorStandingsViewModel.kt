package com.toquete.boxbox.feature.constructorstandings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.fullconstructorstandings.GetConstructorStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
internal class ConstructorStandingsViewModel @Inject constructor(
    private val getConstructorStandingsUseCase: GetConstructorStandingsUseCase
) : ViewModel() {

    private val _state = MutableStateFlow<ConstructorStandingsState>(ConstructorStandingsState.Loading)
    val state = _state.asStateFlow()

    init {
        getConstructorStandings()
    }

    private fun getConstructorStandings() {
        viewModelScope.launch {
            _state.update { ConstructorStandingsState.Loading }
            runCatching {
                getConstructorStandingsUseCase()
            }.onSuccess { data ->
                _state.update { ConstructorStandingsState.Success(data) }
            }.onFailure {
                _state.update { ConstructorStandingsState.Error }
            }
        }
    }
}