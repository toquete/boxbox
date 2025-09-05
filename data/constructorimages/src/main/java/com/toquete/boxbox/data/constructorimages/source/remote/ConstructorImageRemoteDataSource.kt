package com.toquete.boxbox.data.constructorimages.source.remote

import com.toquete.boxbox.core.network.model.ConstructorImageResponse

internal fun interface ConstructorImageRemoteDataSource {

    suspend fun getConstructorsImages(): List<ConstructorImageResponse>
}
