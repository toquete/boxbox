package com.toquete.boxbox.core.network.firebase

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import dev.gitlive.firebase.firestore.FirebaseFirestore

internal class FirebaseDatabase(
    private val firestore: FirebaseFirestore
) : BoxBoxRemoteDatabase {

    override suspend fun <T> getCollection(id: String, type: Class<T>): List<T> {
        return emptyList()

    }
    override suspend fun <T> getDocument(collection: String, id: String, type: Class<T>): T? {
        return null
    }
}
