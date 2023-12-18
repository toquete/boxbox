package com.toquete.boxbox.data.constructorcolors.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import javax.inject.Inject

private const val COLLECTION = "constructor_color"

internal class DefaultConstructorColorRemoteDataSource @Inject constructor(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : ConstructorColorRemoteDataSource {

    override suspend fun getConstructorsColors(): List<ConstructorColorResponse> {
        return remoteDatabase.getCollection(COLLECTION, ConstructorColorResponse::class.java)
    }
}
