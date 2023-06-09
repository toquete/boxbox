package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.DriverImageResponse
import javax.inject.Inject

private const val COLLECTION = "driver_image"

internal class DefaultDriverImageRemoteDataSource @Inject constructor(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : DriverImageRemoteDataSource {

    override suspend fun getDriversImages(): List<DriverImageResponse> {
        return remoteDatabase.getCollection(COLLECTION, DriverImageResponse::class.java)
    }
}
