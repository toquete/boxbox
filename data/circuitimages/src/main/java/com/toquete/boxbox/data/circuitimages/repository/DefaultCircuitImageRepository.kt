package com.toquete.boxbox.data.circuitimages.repository

import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.data.circuitimages.model.toEntity
import com.toquete.boxbox.data.circuitimages.source.local.CircuitImageLocalDataSource
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import javax.inject.Inject

internal class DefaultCircuitImageRepository @Inject constructor(
    private val remoteDataSource: CircuitImageRemoteDataSource,
    private val localDataSource: CircuitImageLocalDataSource
) : CircuitImageRepository {

    override suspend fun sync() {
        remoteDataSource.getCircuitImages()
            .also { list ->
                localDataSource.insertAll(list.map(CircuitImageResponse::toEntity))
            }
    }
}
