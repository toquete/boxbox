package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.database.model.ConstructorStandingEntity
import com.toquete.boxbox.core.network.model.ConstructorResponse
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import com.toquete.boxbox.core.network.model.ConstructorStandingsResponse
import com.toquete.boxbox.core.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.core.network.model.StandingsLists
import com.toquete.boxbox.core.network.model.StandingsTableResponse

val constructorStandingsResponse = listOf(
    ConstructorStandingResponse(
        position = "1",
        points = "123",
        wins = "3",
        constructor = ConstructorResponse(
            id = "red_bull",
            url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
            name = "Red Bull",
            nationality = "Austrian"
        )
    )
)

val constructorStandingsWrapper = ConstructorStandingsWrapper(
    data = StandingsTableResponse(
        standingTable = StandingsLists(
            standingsLists = listOf(
                ConstructorStandingsResponse(
                    constructorStandings = constructorStandingsResponse
                )
            )
        )
    )
)

val constructorStandingEntities = listOf(
    ConstructorStandingEntity(
        position = 1,
        points = "123",
        wins = "3",
        constructorId = "red_bull"
    )
)
