package com.toquete.boxbox.feature.settings.di

import com.toquete.boxbox.feature.settings.ui.SettingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val settingsFeatureModule = module {
    viewModelOf(::SettingsViewModel)
}
