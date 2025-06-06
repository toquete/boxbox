package com.toquete.boxbox.core.network.firebase

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.core.network.model.CountryResponse
import com.toquete.boxbox.core.network.model.DriverImageResponse
import dev.gitlive.firebase.firestore.FirebaseFirestore

internal class FirebaseDatabase(
    private val firestore: FirebaseFirestore
) : BoxBoxRemoteDatabase {

    override suspend fun getCircuitImages(): List<CircuitImageResponse> {
        return firestore.collection("circuit_image")
            .get()
            .documents.map { it.data() }
    }

    override suspend fun getConstructorColors(): List<ConstructorColorResponse> {
        return firestore.collection("constructor_color")
            .get()
            .documents.map { it.data() }
    }

    override suspend fun getConstructorImages(): List<ConstructorImageResponse> {
        return firestore.collection("constructor_image")
            .get()
            .documents.map { it.data() }
    }

    override suspend fun getCountries(): List<CountryResponse> {
        return firestore.collection("country")
            .get()
            .documents.map { it.data() }
    }

    override suspend fun getDriversImages(): List<DriverImageResponse> {
        return firestore.collection("driver_image")
            .get()
            .documents.map { it.data() }
    }
}
