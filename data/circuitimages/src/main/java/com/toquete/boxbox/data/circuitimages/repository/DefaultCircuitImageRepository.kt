package com.toquete.boxbox.data.circuitimages.repository

import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.data.circuitimages.model.toEntity
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import com.toquete.boxbox.domain.repository.CircuitImageRepository
import javax.inject.Inject

internal class DefaultCircuitImageRepository @Inject constructor(
    private val remoteDataSource: CircuitImageRemoteDataSource,
    private val circuitImageDao: CircuitImageDao
) : CircuitImageRepository {

    override suspend fun sync() {
        val list = remoteDataSource.getCircuitImages()
        circuitImageDao.upsertAll(list.map(CircuitImageResponse::toEntity))
    }
}
