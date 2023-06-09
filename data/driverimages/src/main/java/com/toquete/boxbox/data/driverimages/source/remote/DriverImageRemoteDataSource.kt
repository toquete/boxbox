package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.model.DriverImageResponse

internal interface DriverImageRemoteDataSource {

    suspend fun getDriversImages(): List<DriverImageResponse>
}
