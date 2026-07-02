package com.toquete.boxbox.feature.races.di

import com.toquete.boxbox.feature.races.ui.RacesViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val racesFeatureModule = module {
    viewModelOf(::RacesViewModel)
}
