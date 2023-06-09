package com.toquete.boxbox.data.constructorimages.source.local

import com.toquete.boxbox.core.database.dao.ConstructorImageDao
import com.toquete.boxbox.core.database.model.ConstructorImageEntity
import javax.inject.Inject

internal class DefaultConstructorImageLocalDataSource @Inject constructor(
    private val constructorImageDao: ConstructorImageDao
) : ConstructorImageLocalDataSource {

    override suspend fun insertAll(constructorImages: List<ConstructorImageEntity>) {
        return constructorImageDao.upsertAll(constructorImages)
    }
}
