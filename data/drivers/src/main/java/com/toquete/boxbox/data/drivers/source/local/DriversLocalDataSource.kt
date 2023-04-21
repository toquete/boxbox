package com.toquete.boxbox.data.drivers.source.local

import com.toquete.boxbox.database.model.DriverEntity

interface DriversLocalDataSource {

    suspend fun insertAll(drivers: List<DriverEntity>)
}