package com.toquete.boxbox.data.countries.source.remote

import com.google.firebase.firestore.FirebaseFirestore
import com.toquete.boxbox.core.network.model.CountryResponse
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

private const val COLLECTION = "country"

internal class DefaultCountryRemoteDataSource @Inject constructor(
    firestore: FirebaseFirestore
) : CountryRemoteDataSource {

    private val collection = firestore.collection(COLLECTION)

    override suspend fun getCountries(): List<CountryResponse> {
        return collection.get().await().toObjects(CountryResponse::class.java)
    }
}
