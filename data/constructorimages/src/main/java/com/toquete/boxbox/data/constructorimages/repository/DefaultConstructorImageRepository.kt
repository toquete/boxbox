package com.toquete.boxbox.data.constructorimages.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.data.constructorimages.model.toEntity
import com.toquete.boxbox.data.constructorimages.source.local.ConstructorImageLocalDataSource
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultConstructorImageRepository @Inject constructor(
    private val remoteDataSource: ConstructorImageRemoteDataSource,
    private val localDataSource: ConstructorImageLocalDataSource,
    @IoDispatcher private val dispatcher: CoroutineContext
) : ConstructorImageRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getConstructorsImages()
            localDataSource.insertAll(list.map(ConstructorImageResponse::toEntity))
        }
    }
}
