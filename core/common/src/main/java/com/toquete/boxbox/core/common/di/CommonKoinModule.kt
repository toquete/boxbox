package com.toquete.boxbox.core.common.di

import kotlinx.coroutines.Dispatchers
import org.koin.dsl.module
import kotlin.coroutines.CoroutineContext

val commonModule = module {
    single<CoroutineContext> { Dispatchers.IO }
}
