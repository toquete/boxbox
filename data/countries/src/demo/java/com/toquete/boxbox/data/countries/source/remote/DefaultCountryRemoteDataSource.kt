package com.toquete.boxbox.data.countries.source.remote

import com.toquete.boxbox.core.network.model.CountryResponse

internal class DefaultCountryRemoteDataSource : CountryRemoteDataSource {

    override suspend fun getCountries(): List<CountryResponse> {
        return listOf(
            CountryResponse(
                id = "USA",
                name = "United States of America",
                nationality = "American",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/us.svg"
            ),
            CountryResponse(
                id = "GER",
                name = "Germany",
                nationality = "German",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/de.svg"
            ),
            CountryResponse(
                id = "CAN",
                name = "Canada",
                nationality = "Canadian",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/ca.svg"
            ),
            CountryResponse(
                id = "GBR",
                name = "Great Britain",
                nationality = "British",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/gb.svg"
            ),
            CountryResponse(
                id = "DEN",
                name = "Denmark",
                nationality = "Danish",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/dk.svg"
            ),
            CountryResponse(
                id = "MEX",
                name = "Mexico",
                nationality = "Mexican",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/mx.svg"
            ),
            CountryResponse(
                id = "JPN",
                name = "Japan",
                nationality = "Japanese",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/jp.svg"
            ),
            CountryResponse(
                id = "NED",
                name = "Netherlands",
                nationality = "Dutch",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/nl.svg"
            ),
            CountryResponse(
                id = "CHN",
                name = "China",
                nationality = "Chinese",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/cn.svg"
            ),
            CountryResponse(
                id = "AUT",
                name = "Austria",
                nationality = "Austrian",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/at.svg"
            ),
            CountryResponse(
                id = "AUS",
                name = "Australia",
                nationality = "Australian",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/au.svg"
            ),
            CountryResponse(
                id = "FRA",
                name = "France",
                nationality = "French",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/fr.svg"
            ),
            CountryResponse(
                id = "THA",
                name = "Thailand",
                nationality = "Thai",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/th.svg"
            ),
            CountryResponse(
                id = "ESP",
                name = "Spain",
                nationality = "Spanish",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/es.svg"
            ),
            CountryResponse(
                id = "MON",
                name = "Monaco",
                nationality = "Monegasque",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/mc.svg"
            ),
            CountryResponse(
                id = "ITA",
                name = "Italia",
                nationality = "Italian",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/it.svg"
            ),
            CountryResponse(
                id = "SUI",
                name = "Switzerland",
                nationality = "Swiss",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/ch.svg"
            ),
            CountryResponse(
                id = "FIN",
                name = "Finland",
                nationality = "Finnish",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/fi.svg"
            ),
            CountryResponse(
                id = "BRA",
                name = "Brazil",
                nationality = "Brazilian",
                flagUrl = "https://flagicons.lipis.dev/flags/4x3/br.svg"
            )
        )
    }
}
