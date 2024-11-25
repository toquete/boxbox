package com.toquete.boxbox.fake

import com.toquete.boxbox.domain.repository.ConstructorColorRepository

class FakeConstructorColorRepository : ConstructorColorRepository {
    override suspend fun sync() = Unit
}
