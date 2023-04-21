package com.toquete.boxbox.data.constructors.source.local

import com.toquete.boxbox.database.dao.ConstructorDao
import com.toquete.boxbox.database.model.ConstructorEntity
import javax.inject.Inject

class ConstructorsLocalDataSourceImpl @Inject constructor(
    private val constructorDao: ConstructorDao
) : ConstructorsLocalDataSource {

    override suspend fun insertAll(constructors: List<ConstructorEntity>) {
        constructorDao.insertAll(constructors)
    }
}