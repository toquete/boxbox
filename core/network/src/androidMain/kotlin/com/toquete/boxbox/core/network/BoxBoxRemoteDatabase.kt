package com.toquete.boxbox.core.network

interface BoxBoxRemoteDatabase {

    suspend fun <T> getCollection(id: String, type: Class<T>): List<T>
    suspend fun <T> getDocument(collection: String, id: String, type: Class<T>): T?
}
