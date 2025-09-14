package com.toquete.boxbox.data.driverstandings.mock

import com.toquete.boxbox.core.database.model.ConstructorEntity
import com.toquete.boxbox.core.model.Constructor

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
    imageUrl = "http://constructor.com",
    flagUrl = "http://flag.com",
    accentColor = "#3571c6",
    backgroundColor = "#3571c6"
)
