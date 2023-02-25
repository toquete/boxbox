package com.toquete.boxbox.data.source.remote.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://ergast.com/api/f1/"

@OptIn(ExperimentalSerializationApi::class)
object ServiceFactory {

    private val json = Json { ignoreUnknownKeys = true }

    fun <T> create(service: Class<T>) : T {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .build()
            .create(service)
    }
}