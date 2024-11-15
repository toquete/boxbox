package com.toquete.boxbox.data.driverimages.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.network.model.DriverImageResponse
import com.toquete.boxbox.data.driverimages.model.toEntity
import com.toquete.boxbox.data.driverimages.source.local.DriverImageLocalDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultDriverImageRepository @Inject constructor(
    private val remoteDataSource: DriverImageRemoteDataSource,
    private val localDataSource: DriverImageLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineContext
) : DriverImageRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getDriversImages()
            localDataSource.insertAll(list.map(DriverImageResponse::toEntity))
        }
    }
}
