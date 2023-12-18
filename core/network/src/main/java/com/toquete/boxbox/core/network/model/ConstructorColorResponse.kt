package com.toquete.boxbox.core.network.model

import com.google.firebase.firestore.PropertyName

data class ConstructorColorResponse(
    val id: String? = null,
    @get:PropertyName("accent_color")
    @set:PropertyName("accent_color")
    var accentColor: String? = null,
    @get:PropertyName("background_color")
    @set:PropertyName("background_color")
    var backgroundColor: String? = null,
)
