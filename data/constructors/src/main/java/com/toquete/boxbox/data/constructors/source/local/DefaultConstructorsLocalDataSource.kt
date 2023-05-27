package com.toquete.boxbox.data.constructors.source.local

import com.toquete.boxbox.core.database.dao.ConstructorDao
import com.toquete.boxbox.core.database.model.ConstructorEntity
import javax.inject.Inject

class DefaultConstructorsLocalDataSource @Inject constructor(
    private val constructorDao: ConstructorDao
) : ConstructorsLocalDataSource {

    override suspend fun insertAll(constructors: List<ConstructorEntity>) {
        constructorDao.upsertAll(constructors)
    }
}
