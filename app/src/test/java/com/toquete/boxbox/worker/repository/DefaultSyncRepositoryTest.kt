package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.data.constructorimages.repository.ConstructorImageRepository
import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.data.countries.repository.CountryRepository
import com.toquete.boxbox.data.driverimages.repository.DriverImageRepository
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultSyncRepositoryTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val driverStandingsRepository: DriverStandingsRepository = mockk(relaxed = true)
    private val constructorStandingsRepository: ConstructorStandingsRepository = mockk(relaxed = true)
    private val countryRepository: CountryRepository = mockk(relaxed = true)
    private val driverImageRepository: DriverImageRepository = mockk(relaxed = true)
    private val constructorImageRepository: ConstructorImageRepository = mockk(relaxed = true)
    private val repository = DefaultSyncRepository(
        driverStandingsRepository,
        constructorStandingsRepository,
        countryRepository,
        driverImageRepository,
        constructorImageRepository,
        dispatcher
    )

    @Test
    fun `sync should call all repositories`() = runTest(dispatcher) {
        repository.sync()

        coVerify {
            driverStandingsRepository.sync()
            constructorStandingsRepository.sync()
            countryRepository.sync()
            driverImageRepository.sync()
            constructorImageRepository.sync()
        }
    }
}
