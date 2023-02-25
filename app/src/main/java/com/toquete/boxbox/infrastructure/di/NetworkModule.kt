package com.toquete.boxbox.infrastructure.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.toquete.boxbox.data.source.remote.network.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://ergast.com/api/f1/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    private val json = Json { ignoreUnknownKeys = true }

    @NetworkInterceptorOkHttpClient
    @Provides
    fun providesNetworkInterceptorOkHttpClient(
        networkConnectionInterceptor: NetworkConnectionInterceptor
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(networkConnectionInterceptor)
            .build()
    }

    @Provides
    fun providesRetrofit(
        @NetworkInterceptorOkHttpClient okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .build()
    }
}