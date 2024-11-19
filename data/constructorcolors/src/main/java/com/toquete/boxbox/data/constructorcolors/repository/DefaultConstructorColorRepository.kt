package com.toquete.boxbox.data.constructorcolors.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.data.constructorcolors.model.toEntity
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultConstructorColorRepository @Inject constructor(
    private val remoteDataSource: ConstructorColorRemoteDataSource,
    private val constructorColorDao: ConstructorColorDao,
    @IoDispatcher private val dispatcher: CoroutineContext
) : ConstructorColorRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getConstructorsColors()
            constructorColorDao.upsertAll(list.map(ConstructorColorResponse::toEntity))
        }
    }
}
