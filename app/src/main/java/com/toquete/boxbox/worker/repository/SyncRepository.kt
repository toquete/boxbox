package com.toquete.boxbox.worker.repository

import kotlinx.coroutines.CoroutineScope

interface SyncRepository {

    suspend fun sync(scope: CoroutineScope)
}
