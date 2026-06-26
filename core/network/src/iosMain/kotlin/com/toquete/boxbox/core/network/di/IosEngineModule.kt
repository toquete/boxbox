package com.toquete.boxbox.core.network.di

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.darwin.Darwin
import org.koin.dsl.module

val iosEngineModule = module {
    single<HttpClientEngine> { Darwin.create() }
}
