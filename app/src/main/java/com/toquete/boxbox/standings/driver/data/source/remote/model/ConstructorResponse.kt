package com.toquete.boxbox.standings.driver.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConstructorResponse(
    @SerialName("name")
    val name: String
)
