package com.toquete.boxbox.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.toquete.boxbox.core.network.interceptor.HttpLogger
import com.toquete.boxbox.core.network.interceptor.NetworkErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://ergast.com/api/f1/"

@OptIn(ExperimentalSerializationApi::class)
@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    @Provides
    fun providesJson(): Json {
        return Json {
            ignoreUnknownKeys = true
            explicitNulls = false
        }
    }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor(logger = HttpLogger()).apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor(NetworkErrorInterceptor())
            .build()
    }

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        json: Json
    ): Retrofit {
        return getRetrofitBuilder(json)
            .client(okHttpClient)
            .build()
    }

    fun getRetrofitBuilder(
        json: Json,
        baseUrl: HttpUrl = BASE_URL.toHttpUrl()
    ): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
    }
}
