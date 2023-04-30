package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.database.model.ConstructorEntity
import com.toquete.boxbox.model.Constructor

val constructorEntities = listOf(
    ConstructorEntity(
        id = "red_bull",
        url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
        name = "Red Bull",
        nationality = "Austrian"
    )
)

val constructor = Constructor(
    id = "red_bull",
    name = "Red Bull",
    imageUrl = "http://constructor.com"
)