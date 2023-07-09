package com.toquete.boxbox.data.constructorstandings.source.remote

import com.toquete.boxbox.core.network.model.ConstructorResponse
import com.toquete.boxbox.core.network.model.ConstructorStandingResponse
import javax.inject.Inject

internal class DefaultConstructorStandingsRemoteDataSource @Inject constructor() :
    ConstructorStandingsRemoteDataSource {

    override suspend fun getConstructorStandings(): List<ConstructorStandingResponse> {
        return listOf(
            ConstructorStandingResponse(
                position = "1",
                points = "377",
                wins = "9",
                constructor = ConstructorResponse(
                    id = "red_bull",
                    url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
                    name = "Red Bull",
                    nationality = "Austrian"
                )
            ),
            ConstructorStandingResponse(
                position = "2",
                points = "178",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "mercedes",
                    url = "http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One",
                    name = "Mercedes",
                    nationality = "German"
                )
            ),
            ConstructorStandingResponse(
                position = "3",
                points = "175",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "aston_martin",
                    url = "http://en.wikipedia.org/wiki/Aston_Martin_in_Formula_One",
                    name = "Aston Martin",
                    nationality = "British"
                )
            ),
            ConstructorStandingResponse(
                position = "4",
                points = "154",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "ferrari",
                    url = "http://en.wikipedia.org/wiki/Scuderia_Ferrari",
                    name = "Ferrari",
                    nationality = "Italian"
                )
            ),
            ConstructorStandingResponse(
                position = "5",
                points = "47",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "alpine",
                    url = "http://en.wikipedia.org/wiki/Alpine_F1_Team",
                    name = "Alpine F1 Team",
                    nationality = "French"
                )
            ),
            ConstructorStandingResponse(
                position = "6",
                points = "29",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "mclaren",
                    url = "http://en.wikipedia.org/wiki/McLaren",
                    name = "McLaren",
                    nationality = "British"
                )
            ),
            ConstructorStandingResponse(
                position = "7",
                points = "11",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "haas",
                    url = "http://en.wikipedia.org/wiki/Haas_F1_Team",
                    name = "Haas F1 Team",
                    nationality = "American"
                )
            ),
            ConstructorStandingResponse(
                position = "8",
                points = "9",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "alfa",
                    url = "http://en.wikipedia.org/wiki/Alfa_Romeo_in_Formula_One",
                    name = "Alfa Romeo",
                    nationality = "Swiss"
                )
            ),
            ConstructorStandingResponse(
                position = "9",
                points = "7",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "williams",
                    url = "http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering",
                    name = "Williams",
                    nationality = "British"
                )
            ),
            ConstructorStandingResponse(
                position = "10",
                points = "2",
                wins = "0",
                constructor = ConstructorResponse(
                    id = "alphatauri",
                    url = "http://en.wikipedia.org/wiki/Scuderia_AlphaTauri",
                    name = "AlphaTauri",
                    nationality = "Italian"
                )
            )
        )
    }
}
