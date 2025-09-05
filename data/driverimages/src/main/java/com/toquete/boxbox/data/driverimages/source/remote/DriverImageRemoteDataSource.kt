package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.model.DriverImageResponse

internal fun interface DriverImageRemoteDataSource {

    suspend fun getDriversImages(): List<DriverImageResponse>
}
