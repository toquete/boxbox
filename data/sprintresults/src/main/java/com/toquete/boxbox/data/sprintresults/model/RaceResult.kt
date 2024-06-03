package com.toquete.boxbox.data.sprintresults.model

import com.toquete.boxbox.core.database.model.SprintRaceResultEntity
import com.toquete.boxbox.core.database.model.SprintRaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.network.model.RaceResponse

internal fun List<RaceResponse>.toEntity(): List<SprintRaceResultEntity> {
    return flatMap { race ->
        race.sprintResults?.map { raceResult ->
            SprintRaceResultEntity(
                season = race.season,
                round = race.round.toInt(),
                position = raceResult.racePosition.toInt(),
                points = raceResult.points.toInt(),
                driverId = raceResult.driver.id,
                constructorId = raceResult.constructor.id,
                gridPosition = raceResult.gridPosition.toInt(),
                laps = raceResult.laps,
                status = raceResult.status,
                time = raceResult.time?.time
            )
        }.orEmpty()
    }
}

internal fun SprintRaceResultWithDriverAndConstructorEntity.toDomain(): RaceResult {
    return RaceResult(
        season = sprintRaceResult.season,
        round = sprintRaceResult.round,
        position = sprintRaceResult.position,
        points = sprintRaceResult.points,
        driver = Driver(
            id = driver.id,
            firstName = driver.firstName,
            lastName = driver.lastName
        ),
        constructor = Constructor(
            id = constructor.id,
            name = constructor.name
        ),
        gridPosition = sprintRaceResult.gridPosition,
        laps = sprintRaceResult.laps,
        status = sprintRaceResult.status,
        time = sprintRaceResult.time
    )
}
