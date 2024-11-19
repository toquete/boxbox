package com.toquete.boxbox.data.circuitimages.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.CircuitImageDao
import com.toquete.boxbox.core.network.model.CircuitImageResponse
import com.toquete.boxbox.data.circuitimages.model.toEntity
import com.toquete.boxbox.data.circuitimages.source.remote.CircuitImageRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultCircuitImageRepository @Inject constructor(
    private val remoteDataSource: CircuitImageRemoteDataSource,
    private val circuitImageDao: CircuitImageDao,
    @IoDispatcher private val dispatcher: CoroutineContext
) : CircuitImageRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getCircuitImages()
            circuitImageDao.upsertAll(list.map(CircuitImageResponse::toEntity))
        }
    }
}
