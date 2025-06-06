package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import kotlinx.coroutines.flow.single

internal class DefaultConstructorImageRemoteDataSource(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : ConstructorImageRemoteDataSource {

    override suspend fun getConstructorsImages(): List<ConstructorImageResponse> {
        return remoteDatabase.getConstructorImages().single()
    }
}
