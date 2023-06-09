package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.model.DriverImageResponse

interface DriverImageRemoteDataSource {

    suspend fun getDriversImages(): List<DriverImageResponse>
}
