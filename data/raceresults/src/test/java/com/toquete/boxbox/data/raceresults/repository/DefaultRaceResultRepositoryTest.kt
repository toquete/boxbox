package com.toquete.boxbox.data.raceresults.repository

import com.toquete.boxbox.core.database.dao.RaceResultDao
import com.toquete.boxbox.data.raceresults.fake.FakeRaceResultDao
import com.toquete.boxbox.data.raceresults.fake.FakeRaceResultRemoteDataSource
import com.toquete.boxbox.data.raceresults.mock.raceResultEntities
import com.toquete.boxbox.data.raceresults.mock.raceResults
import com.toquete.boxbox.data.raceresults.source.remote.RaceResultRemoteDataSource
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertContentEquals

class DefaultRaceResultRepositoryTest {

    private lateinit var remoteDataSource: RaceResultRemoteDataSource
    private lateinit var raceResultDao: RaceResultDao
    private lateinit var repository: DefaultRaceResultRepository

    @Before
    fun setUp() {
        remoteDataSource = FakeRaceResultRemoteDataSource()
        raceResultDao = FakeRaceResultDao()
        repository = DefaultRaceResultRepository(
            remoteDataSource = remoteDataSource,
            raceResultDao = raceResultDao
        )
    }

    @Test
    fun `getRaceResultsBySeason should return mapped list when called`() = runTest {
        val result = repository.getRaceResultsBySeasonAndRound(season = "2023", round = 1).first()

        assertContentEquals(raceResults, result)
    }

    @Test
    fun `sync should insert race result data in database when remote data is gotten successfully`() = runTest {
        repository.sync()

        val result = raceResultDao.getRaceResults()

        assertContentEquals(raceResultEntities, result.first())
    }
}
