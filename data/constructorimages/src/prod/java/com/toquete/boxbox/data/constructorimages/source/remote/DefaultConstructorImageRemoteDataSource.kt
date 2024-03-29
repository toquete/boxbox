package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import javax.inject.Inject

private const val COLLECTION = "constructor_image"

internal class DefaultConstructorImageRemoteDataSource @Inject constructor(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : ConstructorImageRemoteDataSource {

    override suspend fun getConstructorsImages(): List<ConstructorImageResponse> {
        return remoteDatabase.getCollection(COLLECTION, ConstructorImageResponse::class.java)
    }
}
