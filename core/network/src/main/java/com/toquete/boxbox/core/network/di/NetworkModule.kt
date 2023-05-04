package com.toquete.boxbox.core.network.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://ergast.com/api/f1/"

@Module
@InstallIn(SingletonComponent::class)
internal object NetworkModule {

    private val json = Json { ignoreUnknownKeys = true }

    @Provides
    fun providesOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
    }

    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        return getRetrofitBuilder()
            .client(okHttpClient)
            .build()
    }

    fun getRetrofitBuilder(baseUrl: HttpUrl = BASE_URL.toHttpUrl()): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
    }
}
