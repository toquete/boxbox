package com.toquete.boxbox.fake

import com.toquete.boxbox.domain.repository.CircuitImageRepository

class FakeCircuitImageRepository : CircuitImageRepository {

    override suspend fun sync() = Unit
}
