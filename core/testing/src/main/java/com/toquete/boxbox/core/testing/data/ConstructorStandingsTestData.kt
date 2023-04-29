package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.database.model.ConstructorStandingEntity
import com.toquete.boxbox.network.model.ConstructorResponse
import com.toquete.boxbox.network.model.ConstructorStandingResponse
import com.toquete.boxbox.network.model.ConstructorStandingsResponse
import com.toquete.boxbox.network.model.ConstructorStandingsWrapper
import com.toquete.boxbox.network.model.StandingsLists
import com.toquete.boxbox.network.model.StandingsTableResponse

val constructorStandingsResponse = ConstructorStandingsWrapper(
    data = StandingsTableResponse(
        standingTable = StandingsLists(
            standingsLists = listOf(
                ConstructorStandingsResponse(
                    constructorStandings = listOf(
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
                )
            )
        )
    )
)

val constructorStandingEntities = listOf(
    ConstructorStandingEntity(
        position = 1,
        points = "100",
        wins = "5",
        constructorId = "red_bull"
    )
)