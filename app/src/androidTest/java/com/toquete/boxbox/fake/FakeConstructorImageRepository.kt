package com.toquete.boxbox.fake

import com.toquete.boxbox.domain.repository.ConstructorImageRepository

class FakeConstructorImageRepository : ConstructorImageRepository {
    override suspend fun sync() = Unit
}
