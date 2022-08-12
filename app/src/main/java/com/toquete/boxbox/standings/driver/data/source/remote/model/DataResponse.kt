package com.toquete.boxbox.standings.driver.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DataResponse<T>(
    @SerialName("MRData")
    val data: T
)
