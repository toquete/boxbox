package com.toquete.boxbox.feature.settings.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.domain.repository.UserPreferencesRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.char
import kotlinx.datetime.toLocalDateTime

internal class SettingsViewModel(
    private val preferencesRepository: UserPreferencesRepository,
    private val timeZone: TimeZone
) : ViewModel() {

    val state = preferencesRepository.userPreferences
        .map {
            SettingsState(
                isLoading = false,
                darkThemeConfig = it.darkThemeConfig,
                colorConfig = it.colorConfig,
                lastUpdatedTime = getLastUpdatedTime(it.lastUpdatedDateInMillis)
            )
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = SettingsState()
        )

    fun onThemeSettingsItemClick(darkThemeConfig: DarkThemeConfig) {
        viewModelScope.launch {
            preferencesRepository.setDarkThemeConfig(darkThemeConfig)
        }
    }

    fun onColorSettingsItemClick(colorConfig: ColorConfig) {
        viewModelScope.launch {
            preferencesRepository.setColorConfig(colorConfig)
        }
    }

    private fun getLastUpdatedTime(timeInMillis: Long?): String? {
        return timeInMillis?.let {
            val format = LocalDateTime.Format {
                date(LocalDate.Formats.ISO)
                char(' ')
                hour()
                char(':')
                minute()
                char(':')
                second()
            }

            Instant.fromEpochMilliseconds(it)
                .toLocalDateTime(timeZone)
                .format(format)
        }
    }
}
