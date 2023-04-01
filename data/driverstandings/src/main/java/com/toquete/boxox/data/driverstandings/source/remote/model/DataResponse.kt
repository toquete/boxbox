package com.toquete.boxox.data.driverstandings.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DataResponse<T>(
    @SerialName("MRData")
    val data: T
)
