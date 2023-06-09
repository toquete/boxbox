package com.toquete.boxbox.data.driverimages.repository

import com.toquete.boxbox.core.network.model.DriverImageResponse
import com.toquete.boxbox.data.driverimages.model.toEntity
import com.toquete.boxbox.data.driverimages.source.local.DriverImageLocalDataSource
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import javax.inject.Inject

internal class DefaultDriverImageRepository @Inject constructor(
    private val remoteDataSource: DriverImageRemoteDataSource,
    private val localDataSource: DriverImageLocalDataSource
) : DriverImageRepository {

    override suspend fun sync(): Boolean {
        return runCatching {
            remoteDataSource.getDriversImages()
                .also { list ->
                    localDataSource.insertAll(list.map(DriverImageResponse::toEntity))
                }
        }.isSuccess
    }
}
