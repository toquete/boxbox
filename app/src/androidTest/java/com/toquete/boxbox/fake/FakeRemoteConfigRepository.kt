package com.toquete.boxbox.fake

import com.toquete.boxbox.core.model.RemoteConfigs
import com.toquete.boxbox.domain.repository.RemoteConfigRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class FakeRemoteConfigRepository : RemoteConfigRepository {
    override val remoteConfigs: Flow<RemoteConfigs>
        get() = flowOf(RemoteConfigs())

    override fun fetchAndActivate(): Flow<Boolean> = flowOf(true)
}