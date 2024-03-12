package com.toquete.boxbox.core.network.model

import com.google.firebase.firestore.PropertyName

data class CountryResponse(
    val id: String? = null,
    val name: String? = null,
    val nationality: String? = null,
    @get:PropertyName("flag_url")
    @set:PropertyName("flag_url")
    var flagUrl: String? = null,
    @get:PropertyName("alternative_id")
    @set:PropertyName("alternative_id")
    var alternativeId: String? = null
)
