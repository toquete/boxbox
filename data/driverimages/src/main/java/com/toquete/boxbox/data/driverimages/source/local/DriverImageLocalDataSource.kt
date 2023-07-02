package com.toquete.boxbox.data.driverimages.source.local

import com.toquete.boxbox.core.database.model.DriverImageEntity

internal interface DriverImageLocalDataSource {

    suspend fun insertAll(driverImages: List<DriverImageEntity>)
}
