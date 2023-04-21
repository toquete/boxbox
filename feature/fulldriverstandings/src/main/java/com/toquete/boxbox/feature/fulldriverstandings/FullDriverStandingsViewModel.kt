package com.toquete.boxbox.feature.fulldriverstandings

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.fulldriverstandings.GetFullDriverStandingsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class FullDriverStandingsViewModel @Inject constructor(
    getFullDriverStandingsUseCase: GetFullDriverStandingsUseCase
) : ViewModel() {

    val state = getFullDriverStandingsUseCase()
        .map { FullDriverStandingsState.Success(it) }
        .onStart { FullDriverStandingsState.Loading }
        .catch { Log.e("XABLAU", "ERRO", it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = FullDriverStandingsState.Loading
        )
}