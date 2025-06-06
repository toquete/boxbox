package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.BoxBoxRemoteDatabase
import com.toquete.boxbox.core.network.model.DriverImageResponse
import kotlinx.coroutines.flow.single

internal class DefaultDriverImageRemoteDataSource(
    private val remoteDatabase: BoxBoxRemoteDatabase
) : DriverImageRemoteDataSource {

    override suspend fun getDriversImages(): List<DriverImageResponse> {
        return remoteDatabase.getDriversImages().single()
    }
}
