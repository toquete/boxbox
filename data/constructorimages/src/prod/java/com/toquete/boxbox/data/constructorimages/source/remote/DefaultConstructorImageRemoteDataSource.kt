package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.ConstructorImageResponse

internal class DefaultConstructorImageRemoteDataSource(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : ConstructorImageRemoteDataSource {

    override suspend fun getConstructorsImages(): List<ConstructorImageResponse> {
        return remoteDatabase.getConstructorImages()
    }
}
