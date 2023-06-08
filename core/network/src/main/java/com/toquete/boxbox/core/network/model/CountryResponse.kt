package com.toquete.boxbox.core.network.model

import com.google.firebase.firestore.PropertyName

data class CountryResponse(
    val id: String,
    val name: String,
    val nationality: String,
    @PropertyName("flag_url")
    val flagUrl: String
)
