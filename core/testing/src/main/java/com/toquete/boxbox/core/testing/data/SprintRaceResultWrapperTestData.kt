package com.toquete.boxbox.core.testing.data

import com.toquete.boxbox.core.network.model.RaceDataResponse
import com.toquete.boxbox.core.network.model.RaceTableResponse
import com.toquete.boxbox.core.network.model.RacesWrapper

val sprintRaceResultWrapper = RacesWrapper(
    data = RaceTableResponse(
        raceTable = RaceDataResponse(
            season = "2023",
            races = sprintRacesResponse
        )
    )
)
