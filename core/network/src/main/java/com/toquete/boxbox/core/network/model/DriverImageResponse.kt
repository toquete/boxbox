package com.toquete.boxbox.core.network.model

import com.google.firebase.firestore.PropertyName

data class DriverImageResponse(
    val id: String? = null,
    @get:PropertyName("image_url")
    @set:PropertyName("image_url")
    var imageUrl: String? = null
)
