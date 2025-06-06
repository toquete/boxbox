package com.toquete.boxbox.core.network.di

import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import org.koin.core.module.Module
import org.koin.dsl.module

internal actual val platformModule: Module = module {
    single<HttpClientEngine> { OkHttpEngine(config = OkHttpConfig()) }
}
