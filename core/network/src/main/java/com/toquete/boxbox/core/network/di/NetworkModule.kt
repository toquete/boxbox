package com.toquete.boxbox.core.network.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.KtorService
import com.toquete.boxbox.core.network.firebase.FirebaseDatabase
import com.toquete.boxbox.core.network.interceptor.HttpLogger
import com.toquete.boxbox.core.network.interceptor.NetworkErrorInterceptor
import io.ktor.client.engine.*
import io.ktor.client.engine.okhttp.*
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module

val networkModule = module {
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor(logger = HttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(NetworkErrorInterceptor())
            .build()
    }
    single<BoxBoxService> { KtorService(engine = get()) }
    single<FirebaseFirestore> { Firebase.firestore }
    single<BoxBoxRemoteDatabase> { FirebaseDatabase(firestore = get()) }
    single<HttpClientEngine> {
        OkHttpEngine(
            config = OkHttpConfig().apply {
                preconfigured = get<OkHttpClient>()
            }
        )
    }
}
