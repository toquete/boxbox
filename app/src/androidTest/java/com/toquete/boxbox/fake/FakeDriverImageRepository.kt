package com.toquete.boxbox.fake

import com.toquete.boxbox.domain.repository.DriverImageRepository

class FakeDriverImageRepository : DriverImageRepository {
    override suspend fun sync() = Unit
}
