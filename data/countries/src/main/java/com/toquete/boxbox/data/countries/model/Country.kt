package com.toquete.boxbox.data.countries.model

import com.toquete.boxbox.core.database.model.CountryEntity
import com.toquete.boxbox.core.network.model.CountryResponse

internal fun CountryResponse.toEntity(): CountryEntity {
    return CountryEntity(
        id = id,
        name = name,
        nationality = nationality,
        flagUrl = flagUrl
    )
}
