package com.toquete.boxbox.data.drivers.source.local

import com.toquete.boxbox.database.dao.DriverDao
import com.toquete.boxbox.database.model.DriverEntity
import javax.inject.Inject

internal class DriversLocalDataSourceImpl @Inject constructor(
    private val driverDao: DriverDao
) : DriversLocalDataSource {

    override suspend fun insertAll(drivers: List<DriverEntity>) {
        driverDao.insertAll(drivers)
    }
}