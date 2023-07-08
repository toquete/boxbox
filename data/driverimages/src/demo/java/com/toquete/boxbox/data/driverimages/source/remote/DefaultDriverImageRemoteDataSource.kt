package com.toquete.boxbox.data.driverimages.source.remote

import com.toquete.boxbox.core.network.model.DriverImageResponse
import javax.inject.Inject

internal class DefaultDriverImageRemoteDataSource @Inject constructor() : DriverImageRemoteDataSource {

    override suspend fun getDriversImages(): List<DriverImageResponse> {
        return listOf(
            DriverImageResponse(
                id = "norris",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/L/LANNOR01_Lando_Norris/lannor01.png"
            ),
            DriverImageResponse(
                id = "albon",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/A/ALEALB01_Alexander_Albon/alealb01.png"
            ),
            DriverImageResponse(
                id = "max_verstappen",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/M/MAXVER01_Max_Verstappen/maxver01.png"
            ),
            DriverImageResponse(
                id = "de_vries",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/N/NYCDEV01_Nyck_De%20Vries/nycdev01.png"
            ),
            DriverImageResponse(
                id = "piastri",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/O/OSCPIA01_Oscar_Piastri/oscpia01.png"
            ),
            DriverImageResponse(
                id = "sargeant",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/L/LOGSAR01_Logan_Sargeant/logsar01.png"
            ),
            DriverImageResponse(
                id = "bottas",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/V/VALBOT01_Valtteri_Bottas/valbot01.png"
            ),
            DriverImageResponse(
                id = "zhou",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/G/GUAZHO01_Guanyu_Zhou/guazho01.png"
            ),
            DriverImageResponse(
                id = "kevin_magnussen",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/K/KEVMAG01_Kevin_Magnussen/kevmag01.png"
            ),
            DriverImageResponse(
                id = "hulkenberg",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/K/KEVMAG01_Kevin_Magnussen/kevmag01.png"
            ),
            DriverImageResponse(
                id = "perez",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/S/SERPER01_Sergio_Perez/serper01.png"
            ),
            DriverImageResponse(
                id = "sainz",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/C/CARSAI01_Carlos_Sainz/carsai01.png"
            ),
            DriverImageResponse(
                id = "stroll",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/L/LANSTR01_Lance_Stroll/lanstr01.png"
            ),
            DriverImageResponse(
                id = "russell",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/G/GEORUS01_George_Russell/georus01.png"
            ),
            DriverImageResponse(
                id = "alonso",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/F/FERALO01_Fernando_Alonso/feralo01.png"
            ),
            DriverImageResponse(
                id = "ocon",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/E/ESTOCO01_Esteban_Ocon/estoco01.png"
            ),
            DriverImageResponse(
                id = "gasly",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/P/PIEGAS01_Pierre_Gasly/piegas01.png"
            ),
            DriverImageResponse(
                id = "tsunoda",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/Y/YUKTSU01_Yuki_Tsunoda/yuktsu01.png"
            ),
            DriverImageResponse(
                id = "leclerc",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/C/CHALEC01_Charles_Leclerc/chalec01.png"
            ),
            DriverImageResponse(
                id = "hamilton",
                imageUrl =
                "https://media.formula1.com/content/dam/fom-website/drivers/L/LEWHAM01_Lewis_Hamilton/lewham01.png"
            )
        )
    }
}
