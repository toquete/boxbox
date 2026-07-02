package com.toquete.boxbox.di

import org.koin.dsl.module
import timber.log.Timber

val treeModule = module {
    single<Timber.Tree> { Timber.DebugTree() }
}
