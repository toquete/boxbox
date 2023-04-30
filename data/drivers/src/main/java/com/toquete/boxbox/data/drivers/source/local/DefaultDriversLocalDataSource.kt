package com.toquete.boxbox.data.drivers.source.local

import com.toquete.boxbox.core.database.dao.DriverDao
import com.toquete.boxbox.core.database.model.DriverEntity
import javax.inject.Inject

internal class DefaultDriversLocalDataSource @Inject constructor(
    private val driverDao: DriverDao
) : DriversLocalDataSource {

    override suspend fun insertAll(drivers: List<DriverEntity>) {
        driverDao.insertAll(drivers)
    }
}