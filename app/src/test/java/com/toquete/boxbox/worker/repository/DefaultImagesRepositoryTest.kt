package com.toquete.boxbox.worker.repository

import com.toquete.boxbox.domain.repository.CircuitImageRepository
import com.toquete.boxbox.domain.repository.ConstructorImageRepository
import com.toquete.boxbox.domain.repository.CountryRepository
import com.toquete.boxbox.domain.repository.DriverImageRepository
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DefaultImagesRepositoryTest {

    private val countryRepository: CountryRepository = mockk(relaxed = true)
    private val driverImageRepository: DriverImageRepository = mockk(relaxed = true)
    private val constructorImageRepository: ConstructorImageRepository = mockk(relaxed = true)
    private val circuitImageRepository: CircuitImageRepository = mockk(relaxed = true)
    private val repository = ImagesRepository(
        countryRepository,
        driverImageRepository,
        constructorImageRepository,
        circuitImageRepository
    )

    @Test
    fun `sync should call all repositories`() = runTest {
        repository.sync()
        advanceUntilIdle()

        coVerify {
            countryRepository.sync()
            driverImageRepository.sync()
            constructorImageRepository.sync()
            circuitImageRepository.sync()
        }
    }
}
