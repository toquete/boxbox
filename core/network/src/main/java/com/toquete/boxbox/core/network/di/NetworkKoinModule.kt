package com.toquete.boxbox.core.network.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.firebase.FirebaseDatabase
import com.toquete.boxbox.core.network.interceptor.HttpLogger
import com.toquete.boxbox.core.network.interceptor.NetworkErrorInterceptor
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val BASE_URL = "https://api.jolpi.ca/ergast/f1/"
private const val JSON_MEDIA_TYPE = "application/json"

val networkModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor(HttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(NetworkErrorInterceptor())
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(get<Json>().asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .client(get())
            .build()
    }

    single<BoxBoxService> { get<Retrofit>().create(BoxBoxService::class.java) }

    single<FirebaseFirestore> { Firebase.firestore }

    single<BoxBoxRemoteDatabase> { FirebaseDatabase(get()) }
}
