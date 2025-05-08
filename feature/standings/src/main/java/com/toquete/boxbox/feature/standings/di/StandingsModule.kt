package com.toquete.boxbox.feature.standings.di

import com.toquete.boxbox.feature.standings.constructors.ConstructorStandingsViewModel
import com.toquete.boxbox.feature.standings.drivers.DriverStandingsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val standingsModule = module {
    viewModelOf(::ConstructorStandingsViewModel)
    viewModelOf(::DriverStandingsViewModel)
}
