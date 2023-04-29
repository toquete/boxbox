package com.toquete.boxbox.data.constructorstandings

import com.toquete.boxbox.database.model.ConstructorStandingEntity

object FakeLocalData {

    val constructorStandings = listOf(
        ConstructorStandingEntity(
            position = 1,
            points = "100",
            wins = "5",
            constructorId = "red_bull"
        )
    )
}