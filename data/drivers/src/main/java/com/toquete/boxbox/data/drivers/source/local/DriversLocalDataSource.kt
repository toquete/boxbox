package com.toquete.boxbox.data.drivers.source.local

import com.toquete.boxbox.database.model.DriverEntity

internal interface DriversLocalDataSource {

    suspend fun getDriverById(id: String): DriverEntity

    suspend fun insertAll(drivers: List<DriverEntity>)
}