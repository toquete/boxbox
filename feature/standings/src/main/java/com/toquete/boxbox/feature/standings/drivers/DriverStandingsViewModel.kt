package com.toquete.boxbox.feature.standings.drivers

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.core.common.extension.isEven
import com.toquete.boxbox.domain.repository.DriverStandingsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@HiltViewModel
internal class DriverStandingsViewModel @Inject constructor(
    repository: DriverStandingsRepository
) : ViewModel() {

    val state = repository.getDriverStandings()
        .map { standings ->
            val list = mutableListOf<Any>()
            standings.indices.forEach { index ->
                if (!index.isEven()) {
                    list.add(Unit)
                }
                list.add(standings[index])
            }
            DriverStandingsState(list)
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = DriverStandingsState()
        )
}
