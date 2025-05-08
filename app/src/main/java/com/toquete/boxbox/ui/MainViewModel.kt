package com.toquete.boxbox.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainViewModel @Inject constructor(
    preferencesRepository: UserPreferencesRepository,
    remoteConfigRepository: RemoteConfigRepository
) : ViewModel() {

    val state: StateFlow<MainState> = combine(
        preferencesRepository.userPreferences,
        remoteConfigRepository.fetchAndActivate()
    ) { preferences, _ ->
        MainState(
            isLoading = false,
            darkThemeConfig = preferences.darkThemeConfig,
            colorConfig = preferences.colorConfig
        )
    }.catch {
        emit(MainState(isLoading = false))
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5_000),
        initialValue = MainState()
    )
}
