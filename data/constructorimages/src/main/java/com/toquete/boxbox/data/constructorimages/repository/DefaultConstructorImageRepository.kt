package com.toquete.boxbox.data.constructorimages.repository

import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.data.constructorimages.model.toEntity
import com.toquete.boxbox.data.constructorimages.source.local.ConstructorImageLocalDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import javax.inject.Inject

internal class DefaultConstructorImageRepository @Inject constructor(
    private val remoteDataSource: ConstructorImageRemoteDataSource,
    private val localDataSource: ConstructorImageLocalDataSource
) : ConstructorImageRepository {

    override suspend fun sync() {
        remoteDataSource.getConstructorsImages()
            .also { list ->
                localDataSource.insertAll(list.map(ConstructorImageResponse::toEntity))
            }
    }
}
