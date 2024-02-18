package com.toquete.boxbox.data.raceresults.model

import com.toquete.boxbox.core.database.model.RaceResultEntity
import com.toquete.boxbox.core.database.model.RaceResultWithDriverAndConstructorEntity
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.RaceResult
import com.toquete.boxbox.core.network.model.RaceResponse

internal fun List<RaceResponse>.toEntity(): List<RaceResultEntity> {
    return flatMap { race ->
        race.results?.map { raceResult ->
            RaceResultEntity(
                season = race.season,
                round = race.round.toInt(),
                position = raceResult.racePosition.toInt(),
                driverId = raceResult.driver.id,
                constructorId = raceResult.constructor.id,
                gridPosition = raceResult.gridPosition.toInt()
            )
        }.orEmpty()
    }
}

internal fun RaceResultWithDriverAndConstructorEntity.toDomain(): RaceResult {
    return RaceResult(
        season = raceResult.season,
        round = raceResult.round,
        position = raceResult.position,
        driver = Driver(
            id = driver.id,
            firstName = driver.firstName,
            lastName = driver.lastName
        ),
        constructor = Constructor(
            id = constructor.id,
            name = constructor.name
        ),
        gridPosition = raceResult.gridPosition
    )
}
