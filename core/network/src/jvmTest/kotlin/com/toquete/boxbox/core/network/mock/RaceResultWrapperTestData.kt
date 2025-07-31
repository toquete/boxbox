package com.toquete.boxbox.core.network.mock

import com.toquete.boxbox.core.network.model.RaceDataResponse
import com.toquete.boxbox.core.network.model.RaceTableResponse
import com.toquete.boxbox.core.network.model.RacesWrapper

val raceResultWrapper = RacesWrapper(
    data = RaceTableResponse(
        totalPages = 200,
        raceTable = RaceDataResponse(
            season = "2023",
            races = raceResultsResponse
        )
    )
)
