package com.toquete.boxbox.data.driverimages.fake

import com.toquete.boxbox.core.network.model.DriverImageResponse
import com.toquete.boxbox.data.driverimages.mock.driverImageResponses
import com.toquete.boxbox.data.driverimages.source.remote.DriverImageRemoteDataSource

class FakeDriverImageRemoteDataSource : DriverImageRemoteDataSource {
    override suspend fun getDriversImages(): List<DriverImageResponse> = driverImageResponses
}
