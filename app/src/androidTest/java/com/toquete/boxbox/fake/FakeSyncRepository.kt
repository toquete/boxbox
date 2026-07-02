package com.toquete.boxbox.fake

import com.toquete.boxbox.domain.repository.SyncRepository

class FakeSyncRepository : SyncRepository {
    override suspend fun sync() = Unit
}