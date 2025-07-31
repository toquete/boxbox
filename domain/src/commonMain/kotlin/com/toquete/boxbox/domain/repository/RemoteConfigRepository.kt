package com.toquete.boxbox.domain.repository

import com.toquete.boxbox.core.model.RemoteConfigs
import kotlinx.coroutines.flow.Flow

interface RemoteConfigRepository {
    val remoteConfigs: Flow<RemoteConfigs>

    fun fetchAndActivate(): Flow<Boolean>
}
