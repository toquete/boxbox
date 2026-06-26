package com.toquete.boxbox.core.network.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.dsl.module

val androidEngineModule = module {
    single<HttpClientEngine> { OkHttp.create() }
}
