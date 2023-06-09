package com.toquete.boxbox.data.driverimages.source.local

import com.toquete.boxbox.core.database.dao.DriverImageDao
import com.toquete.boxbox.core.database.model.DriverImageEntity
import javax.inject.Inject

internal class DefaultDriverImageLocalDataSource @Inject constructor(
    private val driverImageDao: DriverImageDao
) : DriverImageLocalDataSource {

    override suspend fun insertAll(driverImages: List<DriverImageEntity>) {
        return driverImageDao.upsertAll(driverImages)
    }
}
