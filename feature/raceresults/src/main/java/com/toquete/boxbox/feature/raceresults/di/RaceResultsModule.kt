package com.toquete.boxbox.feature.raceresults.di

import com.toquete.boxbox.feature.raceresults.ui.RaceResultsViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val raceResultsFeatureModule = module {
    viewModelOf(::RaceResultsViewModel)
}
