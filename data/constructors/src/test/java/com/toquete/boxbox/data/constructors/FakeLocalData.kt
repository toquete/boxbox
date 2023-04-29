package com.toquete.boxbox.data.constructors

import com.toquete.boxbox.database.model.ConstructorEntity

object FakeLocalData {

    val constructors = listOf(
        ConstructorEntity(
            id = "red_bull",
            url = "http://redbull.com",
            name = "Red Bull",
            nationality = "Austrian"
        )
    )
}