package com.toquete.boxbox.data.constructorimages.repository

import com.toquete.boxbox.core.common.annotation.IoDispatcher
import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.network.model.ConstructorImageResponse
import com.toquete.boxbox.data.constructorimages.model.toEntity
import com.toquete.boxbox.data.constructorimages.source.remote.ConstructorImageRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

internal class DefaultConstructorImageRepository @Inject constructor(
    private val remoteDataSource: ConstructorImageRemoteDataSource,
    private val constructorImageDao: ConstructorImageDao,
    @IoDispatcher private val dispatcher: CoroutineContext
) : ConstructorImageRepository {

    override suspend fun sync() {
        withContext(dispatcher) {
            val list = remoteDataSource.getConstructorsImages()
            constructorImageDao.upsertAll(list.map(ConstructorImageResponse::toEntity))
        }
    }
}
