package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.model.ConstructorImageResponse

internal interface ConstructorImageRemoteDataSource {

    suspend fun getConstructorsImages(): List<ConstructorImageResponse>
}
