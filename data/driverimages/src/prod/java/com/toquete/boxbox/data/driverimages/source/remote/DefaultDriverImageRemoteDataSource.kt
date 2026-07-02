package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.DriverImageResponse

private const val COLLECTION = "driver_image"

internal class DefaultDriverImageRemoteDataSource(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : DriverImageRemoteDataSource {

    override suspend fun getDriversImages(): List<DriverImageResponse> {
        return remoteDatabase.getCollection(COLLECTION, DriverImageResponse::class.java)
    }
}
