package com.toquete.boxbox.data.drivers.source.local

import com.toquete.boxbox.core.database.model.DriverEntity

interface DriversLocalDataSource {

    suspend fun insertAll(drivers: List<DriverEntity>)
}