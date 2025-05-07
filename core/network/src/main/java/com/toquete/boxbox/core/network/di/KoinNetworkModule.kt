package com.toquete.boxbox.core.network.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.firebase.FirebaseDatabase
import com.toquete.boxbox.core.network.interceptor.HttpLogger
import com.toquete.boxbox.core.network.interceptor.NetworkErrorInterceptor
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://api.jolpi.ca/ergast/f1/"

val networkModule = module {
    single<Json> {
        Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }
    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor(logger = HttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(NetworkErrorInterceptor())
            .build()
    }
    single<BoxBoxService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL.toHttpUrl())
            .addConverterFactory(get<Json>().asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .client(get())
            .build()
            .create(BoxBoxService::class.java)
    }
    single<FirebaseFirestore> { Firebase.firestore }
    single<BoxBoxRemoteDatabase> { FirebaseDatabase(firestore = get()) }
}
