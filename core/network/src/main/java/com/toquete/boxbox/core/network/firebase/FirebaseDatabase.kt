package com.toquete.boxbox.core.network.firebase

import com.google.firebase.firestore.FirebaseFirestore
import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

internal class FirebaseDatabase @Inject constructor(
    private val firestore: FirebaseFirestore
) : BoxBoxRemoteDatabase {

    override suspend fun <T> getCollection(id: String, type: Class<T>): List<T> {
        return firestore.collection(id).get().await().toObjects(type)
    }
    override suspend fun <T> getDocument(collection: String, id: String, type: Class<T>): T? {
        return firestore.collection(collection).document(id).get().await().toObject(type)
    }
}
