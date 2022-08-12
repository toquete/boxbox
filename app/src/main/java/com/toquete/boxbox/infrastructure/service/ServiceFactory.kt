package com.toquete.boxbox.infrastructure.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val BASE_URL = "https://ergast.com/api/f1/"
private const val JSON_MEDIA_TYPE = "application/json"

@OptIn(ExperimentalSerializationApi::class)
object ServiceFactory {

    private val json = Json { ignoreUnknownKeys = true }

    fun getBuilder(baseUrl: HttpUrl = BASE_URL.toHttpUrl()) : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .build()
    }
}