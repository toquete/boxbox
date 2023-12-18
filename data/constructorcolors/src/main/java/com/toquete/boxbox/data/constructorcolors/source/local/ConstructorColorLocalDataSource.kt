package com.toquete.boxbox.data.constructorcolors.source.local

import com.toquete.boxbox.core.database.model.ConstructorColorEntity

internal interface ConstructorColorLocalDataSource {

    suspend fun insertAll(constructorColors: List<ConstructorColorEntity>)
}
