package com.toquete.boxbox.feature.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.repository.UserPreferencesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val preferencesRepository: UserPreferencesRepository
) : ViewModel() {

    val state = preferencesRepository.userPreferences
        .map { SettingsState.Success(it) }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsState.Loading
        )

    fun onSettingsItemClick(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            preferencesRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }
}
