package com.toquete.boxbox.data.constructors.source.local

import com.toquete.boxbox.core.database.model.ConstructorEntity

interface ConstructorsLocalDataSource {

    suspend fun insertAll(constructors: List<ConstructorEntity>)
}
