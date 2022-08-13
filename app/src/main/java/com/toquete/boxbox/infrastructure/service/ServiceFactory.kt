package com.toquete.boxbox.infrastructure.service

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import retrofit2.Retrofit

private const val JSON_MEDIA_TYPE = "application/json"

@OptIn(ExperimentalSerializationApi::class)
abstract class ServiceFactory {

    abstract val baseUrl: String

    private val json = Json { ignoreUnknownKeys = true }

    fun getBuilder() : Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .build()
    }
}