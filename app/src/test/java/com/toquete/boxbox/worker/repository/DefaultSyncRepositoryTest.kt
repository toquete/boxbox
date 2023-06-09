package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.data.constructorstandings.repository.ConstructorStandingsRepository
import com.toquete.boxbox.data.countries.repository.CountryRepository
import com.toquete.boxbox.data.driverimages.repository.DriverImageRepository
import com.toquete.boxbox.data.driverstandings.repository.DriverStandingsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class DefaultSyncRepositoryTest {

    private val dispatcher = UnconfinedTestDispatcher()
    private val driverStandingsRepository: DriverStandingsRepository = mockk(relaxed = true)
    private val constructorStandingsRepository: ConstructorStandingsRepository = mockk(relaxed = true)
    private val countryRepository: CountryRepository = mockk(relaxed = true)
    private val driverImageRepository: DriverImageRepository = mockk(relaxed = true)
    private val repository = DefaultSyncRepository(
        driverStandingsRepository,
        constructorStandingsRepository,
        countryRepository,
        driverImageRepository,
        dispatcher
    )

    @Test
    fun `sync should return true when all repositories returned true`() = runTest(dispatcher) {
        coEvery { driverStandingsRepository.sync() } returns true
        coEvery { constructorStandingsRepository.sync() } returns true
        coEvery { countryRepository.sync() } returns true
        coEvery { driverImageRepository.sync() } returns true

        val result = repository.sync()

        assertTrue(result)
    }

    @Test
    fun `sync should return false when at least one repository returned false`() = runTest(dispatcher) {
        coEvery { driverStandingsRepository.sync() } returns false
        coEvery { constructorStandingsRepository.sync() } returns true
        coEvery { countryRepository.sync() } returns true
        coEvery { driverImageRepository.sync() } returns true

        val result = repository.sync()

        assertFalse(result)
    }
}
