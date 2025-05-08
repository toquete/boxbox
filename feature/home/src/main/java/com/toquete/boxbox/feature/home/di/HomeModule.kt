package com.toquete.boxbox.feature.home.di

import com.toquete.boxbox.feature.home.ui.HomeViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val homeFeatureModule = module {
    viewModelOf(::HomeViewModel)
}
