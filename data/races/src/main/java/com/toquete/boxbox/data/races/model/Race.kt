package com.toquete.boxbox.data.races.model

import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.network.model.RaceResponse

internal fun RaceResponse.toEntity(): RaceEntity {
    return RaceEntity(
        season = season,
        round = round.toInt(),
        url = url,
        name = raceName,
        circuitId = circuit.id,
        date = date,
        time = time,
        firstPracticeDate = firstPractice.date,
        firstPracticeTime = firstPractice.time,
        secondPracticeDate = secondPractice.date,
        secondPracticeTime = secondPractice.time,
        thirdPracticeDate = thirdPractice.date,
        thirdPracticeTime = thirdPractice.time,
        qualifyingDate = qualifying.date,
        qualifyingTime = qualifying.time
    )
}
