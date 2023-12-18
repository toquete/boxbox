package com.toquete.boxbox.data.constructorcolors.source.remote

import com.toquete.boxbox.core.network.model.ConstructorColorResponse

internal interface ConstructorColorRemoteDataSource {

    suspend fun getConstructorsColors(): List<ConstructorColorResponse>
}
