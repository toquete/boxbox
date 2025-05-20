package com.toquete.boxbox.core.model

data class Constructor(
    val id: String,
    val name: String,
    val imageUrl: String? = null,
    val flagUrl: String? = null,
    val accentColor: String? = null,
    val backgroundColor: String? = null
)
