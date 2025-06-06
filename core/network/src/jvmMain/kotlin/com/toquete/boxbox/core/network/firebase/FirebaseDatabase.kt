package com.toquete.boxbox.core.network.firebase

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import dev.gitlive.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class FirebaseDatabase(
    private val firestore: FirebaseFirestore
) : BoxBoxRemoteDatabase {

    override suspend fun <T> getCollection(id: String, type: Class<T>): List<T> {
        return emptyList()
    }

    override suspend fun <T> getDocument(collection: String, id: String, type: Class<T>): T? {
        return null
    }

    override fun getCircuitImages(): Flow<List<CircuitImageResponse>> {
        return firestore.collection("circuit_images")
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    document.data()
                }
            }
    }

    override fun getConstructorColors(): Flow<List<ConstructorColorResponse>> {
        return firestore.collection("constructor_color")
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    document.data()
                }
            }
    }

    override fun getConstructorImages(): Flow<List<ConstructorImageResponse>> {
        return firestore.collection("constructor_image")
            .snapshots
            .map { snapshot ->
                snapshot.documents.map { document ->
                    document.data()
                }
            }
    }
}
