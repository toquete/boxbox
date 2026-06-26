package com.toquete.boxbox.di

import com.google.firebase.Firebase
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.firestore
import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.BoxBoxRetrofitService
import com.toquete.boxbox.core.network.BoxBoxService
import com.toquete.boxbox.core.network.asBoxBoxService
import com.toquete.boxbox.core.network.firebase.FirebaseDatabase
import com.toquete.boxbox.network.HttpLogger
import com.toquete.boxbox.network.NetworkErrorInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

private const val JSON_MEDIA_TYPE = "application/json"
private const val BASE_URL = "https://api.jolpi.ca/ergast/f1/"

// TODO Step 12: delete — replaced by Koin networkModule + androidEngineModule
@Module
@InstallIn(SingletonComponent::class)
internal object HiltNetworkShimModule {

    @Provides
    fun providesJson(): Json = Json {
        ignoreUnknownKeys = true
        explicitNulls = false
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
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, json: Json): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(json.asConverterFactory(JSON_MEDIA_TYPE.toMediaType()))
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun providesBoxBoxService(retrofit: Retrofit): BoxBoxService =
        retrofit.create(BoxBoxRetrofitService::class.java).asBoxBoxService()

    @Provides
    @Singleton
    fun providesFirestore(): FirebaseFirestore = Firebase.firestore

    @Provides
    @Singleton
    fun providesBoxBoxRemoteDatabase(firestore: FirebaseFirestore): BoxBoxRemoteDatabase =
        FirebaseDatabase(firestore)
}
