package com.toquete.boxbox.data.constructorcolors.repository

import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.network.model.ConstructorColorResponse
import com.toquete.boxbox.data.constructorcolors.model.toEntity
import com.toquete.boxbox.data.constructorcolors.source.remote.ConstructorColorRemoteDataSource
import com.toquete.boxbox.domain.repository.ConstructorColorRepository

internal class DefaultConstructorColorRepository(
    private val remoteDataSource: ConstructorColorRemoteDataSource,
    private val constructorColorDao: ConstructorColorDao
) : ConstructorColorRepository {

    override suspend fun sync() {
        val list = remoteDataSource.getConstructorsColors()
        constructorColorDao.upsertAll(list.map(ConstructorColorResponse::toEntity))
    }
}
