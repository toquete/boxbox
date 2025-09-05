package com.toquete.boxbox.data.constructorcolors.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.ConstructorColorResponse

internal class DefaultConstructorColorRemoteDataSource(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : ConstructorColorRemoteDataSource {

    override suspend fun getConstructorsColors(): List<ConstructorColorResponse> {
        return remoteDatabase.getConstructorColors()
    }
}
