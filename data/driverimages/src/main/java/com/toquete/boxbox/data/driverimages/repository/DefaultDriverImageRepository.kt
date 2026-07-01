package com.toquete.boxbox.data.driverimages.repository

import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.network.model.DriverImageResponse
import com.toquete.boxbox.data.driverimages.model.toEntity
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource
import com.toquete.boxbox.domain.repository.DriverImageRepository

internal class DefaultDriverImageRepository(
    private val remoteDataSource: DriverImageRemoteDataSource,
    private val driverImageDao: DriverImageDao
) : DriverImageRepository {

    override suspend fun sync() {
        val list = remoteDataSource.getDriversImages()
        driverImageDao.upsertAll(list.map(DriverImageResponse::toEntity))
    }
}
