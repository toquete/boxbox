package com.toquete.boxbox.di

import com.google.firebase.appcheck.AppCheckProviderFactory
import com.google.firebase.appcheck.playintegrity.PlayIntegrityAppCheckProviderFactory
import org.koin.dsl.module

val appCheckModule = module {
    single<AppCheckProviderFactory> { PlayIntegrityAppCheckProviderFactory.getInstance() }
}
