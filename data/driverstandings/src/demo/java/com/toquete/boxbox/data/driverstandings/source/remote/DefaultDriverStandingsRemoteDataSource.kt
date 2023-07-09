package com.toquete.boxbox.data.driverstandings.source.remote

import com.toquete.boxbox.core.network.model.ConstructorResponse
import com.toquete.boxbox.core.network.model.DriverResponse
import com.toquete.boxbox.core.network.model.DriverStandingResponse
import javax.inject.Inject

internal class DefaultDriverStandingsRemoteDataSource @Inject constructor() : DriverStandingsRemoteDataSource {

    @Suppress("LongMethod")
    override suspend fun getDriverStandings(): List<DriverStandingResponse> {
        return listOf(
            DriverStandingResponse(
                position = "1",
                points = "229",
                wins = "7",
                driver = DriverResponse(
                    id = "max_verstappen",
                    number = "33",
                    code = "VER",
                    url = "http://en.wikipedia.org/wiki/Max_Verstappen",
                    givenName = "Max",
                    familyName = "Verstappen",
                    dateOfBirth = "1997-09-30",
                    nationality = "Dutch"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "red_bull",
                        url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
                        name = "Red Bull",
                        nationality = "Austrian"
                    )
                )
            ),
            DriverStandingResponse(
                position = "2",
                points = "148",
                wins = "2",
                driver = DriverResponse(
                    id = "perez",
                    number = "11",
                    code = "PER",
                    url = "http://en.wikipedia.org/wiki/Sergio_P%C3%A9rez",
                    givenName = "Sergio",
                    familyName = "Pérez",
                    dateOfBirth = "1990-01-26",
                    nationality = "Mexican"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "red_bull",
                        url = "http://en.wikipedia.org/wiki/Red_Bull_Racing",
                        name = "Red Bull",
                        nationality = "Austrian"
                    )
                )
            ),
            DriverStandingResponse(
                position = "3",
                points = "131",
                wins = "0",
                driver = DriverResponse(
                    id = "alonso",
                    number = "14",
                    code = "ALO",
                    url = "http://en.wikipedia.org/wiki/Fernando_Alonso",
                    givenName = "Fernando",
                    familyName = "Alonso",
                    dateOfBirth = "1981-07-29",
                    nationality = "Spanish"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "aston_martin",
                        url = "http://en.wikipedia.org/wiki/Aston_Martin_in_Formula_One",
                        name = "Aston Martin",
                        nationality = "British"
                    )
                )
            ),
            DriverStandingResponse(
                position = "4",
                points = "106",
                wins = "0",
                driver = DriverResponse(
                    id = "hamilton",
                    number = "44",
                    code = "HAM",
                    url = "http://en.wikipedia.org/wiki/Lewis_Hamilton",
                    givenName = "Lewis",
                    familyName = "Hamilton",
                    dateOfBirth = "1985-01-07",
                    nationality = "British"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "mercedes",
                        url = "http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One",
                        name = "Mercedes",
                        nationality = "German"
                    )
                )
            ),
            DriverStandingResponse(
                position = "5",
                points = "82",
                wins = "0",
                driver = DriverResponse(
                    id = "sainz",
                    number = "55",
                    code = "SAI",
                    url = "http://en.wikipedia.org/wiki/Carlos_Sainz_Jr.",
                    givenName = "Carlos",
                    familyName = "Sainz",
                    dateOfBirth = "1994-09-01",
                    nationality = "Spanish"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "ferrari",
                        url = "http://en.wikipedia.org/wiki/Scuderia_Ferrari",
                        name = "Ferrari",
                        nationality = "Italian"
                    )
                )
            ),
            DriverStandingResponse(
                position = "6",
                points = "72",
                wins = "0",
                driver = DriverResponse(
                    id = "leclerc",
                    number = "16",
                    code = "LEC",
                    url = "http://en.wikipedia.org/wiki/Charles_Leclerc",
                    givenName = "Charles",
                    familyName = "Leclerc",
                    dateOfBirth = "1997-10-16",
                    nationality = "Monegasque"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "ferrari",
                        url = "http://en.wikipedia.org/wiki/Scuderia_Ferrari",
                        name = "Ferrari",
                        nationality = "Italian"
                    )
                )
            ),
            DriverStandingResponse(
                position = "7",
                points = "72",
                wins = "0",
                driver = DriverResponse(
                    id = "russell",
                    number = "63",
                    code = "RUS",
                    url = "http://en.wikipedia.org/wiki/George_Russell_%28racing_driver%29",
                    givenName = "George",
                    familyName = "Russell",
                    dateOfBirth = "1998-02-15",
                    nationality = "British"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "mercedes",
                        url = "http://en.wikipedia.org/wiki/Mercedes-Benz_in_Formula_One",
                        name = "Mercedes",
                        nationality = "German"
                    )
                )
            ),
            DriverStandingResponse(
                position = "8",
                points = "44",
                wins = "0",
                driver = DriverResponse(
                    id = "stroll",
                    number = "18",
                    code = "STR",
                    url = "http://en.wikipedia.org/wiki/Lance_Stroll",
                    givenName = "Lance",
                    familyName = "Stroll",
                    dateOfBirth = "1998-10-29",
                    nationality = "Canadian"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "aston_martin",
                        url = "http://en.wikipedia.org/wiki/Aston_Martin_in_Formula_One",
                        name = "Aston Martin",
                        nationality = "British"
                    )
                )
            ),
            DriverStandingResponse(
                position = "9",
                points = "31",
                wins = "0",
                driver = DriverResponse(
                    id = "ocon",
                    number = "31",
                    code = "OCO",
                    url = "http://en.wikipedia.org/wiki/Esteban_Ocon",
                    givenName = "Esteban",
                    familyName = "Ocon",
                    dateOfBirth = "1996-09-17",
                    nationality = "French"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "alpine",
                        url = "http://en.wikipedia.org/wiki/Alpine_F1_Team",
                        name = "Alpine F1 Team",
                        nationality = "French"
                    )
                )
            ),
            DriverStandingResponse(
                position = "10",
                points = "24",
                wins = "0",
                driver = DriverResponse(
                    id = "norris",
                    number = "4",
                    code = "NOR",
                    url = "http://en.wikipedia.org/wiki/Lando_Norris",
                    givenName = "Lando",
                    familyName = "Norris",
                    dateOfBirth = "1999-11-13",
                    nationality = "British"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "mclaren",
                        url = "http://en.wikipedia.org/wiki/McLaren",
                        name = "McLaren",
                        nationality = "British"
                    )
                )
            ),
            DriverStandingResponse(
                position = "11",
                points = "16",
                wins = "0",
                driver = DriverResponse(
                    id = "gasly",
                    number = "10",
                    code = "GAS",
                    url = "http://en.wikipedia.org/wiki/Pierre_Gasly",
                    givenName = "Pierre",
                    familyName = "Gasly",
                    dateOfBirth = "1996-02-07",
                    nationality = "French"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "alpine",
                        url = "http://en.wikipedia.org/wiki/Alpine_F1_Team",
                        name = "Alpine F1 Team",
                        nationality = "French"
                    )
                )
            ),
            DriverStandingResponse(
                position = "12",
                points = "9",
                wins = "0",
                driver = DriverResponse(
                    id = "hulkenberg",
                    number = "27",
                    code = "HUL",
                    url = "http://en.wikipedia.org/wiki/Nico_H%C3%BClkenberg",
                    givenName = "Nico",
                    familyName = "Hulkenberg",
                    dateOfBirth = "1987-08-19",
                    nationality = "German"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "haas",
                        url = "http://en.wikipedia.org/wiki/Haas_F1_Team",
                        name = "Haas F1 Team",
                        nationality = "American"
                    )
                )
            ),
            DriverStandingResponse(
                position = "13",
                points = "7",
                wins = "0",
                driver = DriverResponse(
                    id = "albon",
                    number = "23",
                    code = "ALB",
                    url = "http://en.wikipedia.org/wiki/Alexander_Albon",
                    givenName = "Alexander",
                    familyName = "Albon",
                    dateOfBirth = "1996-03-23",
                    nationality = "Thai"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "williams",
                        url = "http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering",
                        name = "Williams",
                        nationality = "British"
                    )
                )
            ),
            DriverStandingResponse(
                position = "14",
                points = "5",
                wins = "0",
                driver = DriverResponse(
                    id = "piastri",
                    number = "81",
                    code = "PIA",
                    url = "http://en.wikipedia.org/wiki/Oscar_Piastri",
                    givenName = "Oscar",
                    familyName = "Piastri",
                    dateOfBirth = "2001-04-06",
                    nationality = "Australian"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "mclaren",
                        url = "http://en.wikipedia.org/wiki/McLaren",
                        name = "McLaren",
                        nationality = "British"
                    )
                )
            ),
            DriverStandingResponse(
                position = "15",
                points = "5",
                wins = "0",
                driver = DriverResponse(
                    id = "bottas",
                    number = "77",
                    code = "BOT",
                    url = "http://en.wikipedia.org/wiki/Valtteri_Bottas",
                    givenName = "Valtteri",
                    familyName = "Bottas",
                    dateOfBirth = "1989-08-28",
                    nationality = "Finnish"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "alfa",
                        url = "http://en.wikipedia.org/wiki/Alfa_Romeo_in_Formula_One",
                        name = "Alfa Romeo",
                        nationality = "Swiss"
                    )
                )
            ),
            DriverStandingResponse(
                position = "16",
                points = "4",
                wins = "0",
                driver = DriverResponse(
                    id = "zhou",
                    number = "24",
                    code = "ZHO",
                    url = "http://en.wikipedia.org/wiki/Guanyu_Zhou",
                    givenName = "Guanyu",
                    familyName = "Zhou",
                    dateOfBirth = "1999-05-30",
                    nationality = "Chinese"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "alfa",
                        url = "http://en.wikipedia.org/wiki/Alfa_Romeo_in_Formula_One",
                        name = "Alfa Romeo",
                        nationality = "Swiss"
                    )
                )
            ),
            DriverStandingResponse(
                position = "17",
                points = "2",
                wins = "0",
                driver = DriverResponse(
                    id = "tsunoda",
                    number = "22",
                    code = "TSU",
                    url = "http://en.wikipedia.org/wiki/Yuki_Tsunoda",
                    givenName = "Yuki",
                    familyName = "Tsunoda",
                    dateOfBirth = "2000-05-11",
                    nationality = "Japanese"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "alphatauri",
                        url = "http://en.wikipedia.org/wiki/Scuderia_AlphaTauri",
                        name = "AlphaTauri",
                        nationality = "Italian"
                    )
                )
            ),
            DriverStandingResponse(
                position = "18",
                points = "2",
                wins = "0",
                driver = DriverResponse(
                    id = "kevin_magnussen",
                    number = "20",
                    code = "MAG",
                    url = "http://en.wikipedia.org/wiki/Kevin_Magnussen",
                    givenName = "Kevin",
                    familyName = "Magnussen",
                    dateOfBirth = "1992-10-05",
                    nationality = "Danish"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "haas",
                        url = "http://en.wikipedia.org/wiki/Haas_F1_Team",
                        name = "Haas F1 Team",
                        nationality = "American"
                    )
                )
            ),
            DriverStandingResponse(
                position = "19",
                points = "0",
                wins = "0",
                driver = DriverResponse(
                    id = "sargeant",
                    number = "2",
                    code = "SAR",
                    url = "http://en.wikipedia.org/wiki/Logan_Sargeant",
                    givenName = "Logan",
                    familyName = "Sargeant",
                    dateOfBirth = "2000-12-31",
                    nationality = "American"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "williams",
                        url = "http://en.wikipedia.org/wiki/Williams_Grand_Prix_Engineering",
                        name = "Williams",
                        nationality = "British"
                    )
                )
            ),
            DriverStandingResponse(
                position = "20",
                points = "0",
                wins = "0",
                driver = DriverResponse(
                    id = "de_vries",
                    number = "21",
                    code = "DEV",
                    url = "http://en.wikipedia.org/wiki/Nyck_de_Vries",
                    givenName = "Nyck",
                    familyName = "de Vries",
                    dateOfBirth = "1995-02-06",
                    nationality = "Dutch"
                ),
                constructors = listOf(
                    ConstructorResponse(
                        id = "alphatauri",
                        url = "http://en.wikipedia.org/wiki/Scuderia_AlphaTauri",
                        name = "AlphaTauri",
                        nationality = "Italian"
                    )
                )
            ),
        )
    }
}
