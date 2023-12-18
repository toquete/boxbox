package com.toquete.boxbox.data.constructorcolors.repository

import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.data.constructorcolors.model.toEntity
import com.toquete.boxbox.data.constructorcolors.source.local.ConstructorColorLocalDataSource
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import javax.inject.Inject

internal class DefaultConstructorColorRepository @Inject constructor(
    private val remoteDataSource: ConstructorColorRemoteDataSource,
    private val localDataSource: ConstructorColorLocalDataSource
) : ConstructorColorRepository {

    override suspend fun sync() {
        remoteDataSource.getConstructorsColors()
            .also { list ->
                localDataSource.insertAll(list.map(ConstructorColorResponse::toEntity))
            }
    }
}
