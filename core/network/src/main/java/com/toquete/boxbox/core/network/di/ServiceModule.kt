package com.toquete.boxbox.core.network.di

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.firestoreSettings
import com.google.firebase.ktx.Firebase
import com.toquete.boxbox.core.network.BoxBoxService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object ServiceModule {

    @Singleton
    @Provides
    fun providesBoxBoxService(
        retrofit: Retrofit
    ): BoxBoxService {
        return retrofit.create(BoxBoxService::class.java)
    }

    @Singleton
    @Provides
    fun providesFirestore(): FirebaseFirestore {
        return Firebase.firestore.apply {
            useEmulator("10.0.2.2", 8080)
            firestoreSettings = firestoreSettings {
                isPersistenceEnabled = false
            }
        }
    }
}
