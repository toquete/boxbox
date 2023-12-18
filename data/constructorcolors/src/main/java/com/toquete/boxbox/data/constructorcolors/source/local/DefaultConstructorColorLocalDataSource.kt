package com.toquete.boxbox.data.constructorcolors.source.local

import com.toquete.boxbox.core.database.dao.ConstructorColorDao
import com.toquete.boxbox.core.database.model.ConstructorColorEntity
import javax.inject.Inject

internal class DefaultConstructorColorLocalDataSource @Inject constructor(
    private val constructorColorDao: ConstructorColorDao
) : ConstructorColorLocalDataSource {

    override suspend fun insertAll(constructorColors: List<ConstructorColorEntity>) {
        constructorColorDao.upsertAll(constructorColors)
    }
}
