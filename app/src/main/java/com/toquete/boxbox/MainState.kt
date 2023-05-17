package com.toquete.boxbox

import com.toquete.boxbox.core.model.DarkThemeConfig

sealed interface MainState {
    object Loading : MainState
    data class Success(
        val isOnline: Boolean,
        val isSyncing: Boolean,
        val darkThemeConfig: DarkThemeConfig
    ) : MainState
}
