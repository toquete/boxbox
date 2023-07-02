package com.toquete.boxbox.data.constructorimages.source.local

import com.toquete.boxbox.core.database.model.ConstructorImageEntity

internal interface ConstructorImageLocalDataSource {

    suspend fun insertAll(constructorImages: List<ConstructorImageEntity>)
}
