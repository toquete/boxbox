package com.toquete.boxbox.data.races.model

import com.toquete.boxbox.core.common.util.dateAndTimeToInstant
import com.toquete.boxbox.core.database.model.RaceEntity
import com.toquete.boxbox.core.database.model.RaceWithCircuitEntity
import com.toquete.boxbox.core.model.Circuit
import com.toquete.boxbox.core.model.Location
import com.toquete.boxbox.core.model.Race
import com.toquete.boxbox.core.network.model.RaceResponse
import kotlin.time.ExperimentalTime

internal fun RaceResponse.toEntity(): RaceEntity {
    return RaceEntity(
        season = season,
        round = round.toInt(),
        raceUrl = url,
        raceName = raceName,
        circuitId = circuit.id,
        date = date,
        time = time,
        firstPracticeDate = firstPractice?.date,
        firstPracticeTime = firstPractice?.time,
        secondPracticeDate = secondPractice?.date,
        secondPracticeTime = secondPractice?.time,
        thirdPracticeDate = thirdPractice?.date,
        thirdPracticeTime = thirdPractice?.time,
        qualifyingDate = qualifying?.date,
        qualifyingTime = qualifying?.time,
        sprintDate = sprint?.date,
        sprintTime = sprint?.time
    )
}

@OptIn(ExperimentalTime::class)
internal fun RaceWithCircuitEntity.toDomain(): Race {
    return Race(
        season = race.season,
        round = race.round,
        url = race.raceUrl,
        name = race.raceName,
        circuit = Circuit(
            id = circuit.id,
            name = circuit.circuitName,
            url = circuit.circuitUrl,
            location = Location(
                latitude = circuit.latitude,
                longitude = circuit.longitude
            ),
            locality = circuit.locality,
            country = circuit.country,
            flagUrl = flagUrl,
            imageUrl = circuitImageUrl
        ),
        dateTime = dateAndTimeToInstant(race.date, race.time),
        firstPracticeDateTime = dateAndTimeToInstant(race.firstPracticeDate, race.firstPracticeTime),
        secondPracticeDateTime = dateAndTimeToInstant(race.secondPracticeDate, race.secondPracticeTime),
        thirdPracticeDateTime = dateAndTimeToInstant(race.thirdPracticeDate, race.thirdPracticeTime),
        qualifyingDateTime = dateAndTimeToInstant(race.qualifyingDate, race.qualifyingTime),
        sprintDateTime = dateAndTimeToInstant(race.sprintDate, race.sprintTime)
    )
}
