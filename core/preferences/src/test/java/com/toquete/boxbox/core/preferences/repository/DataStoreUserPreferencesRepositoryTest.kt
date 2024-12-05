package com.toquete.boxbox.core.preferences.repository

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.testUserPreferencesDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TemporaryFolder
import kotlin.test.assertEquals
import kotlin.test.assertNull

class DataStoreUserPreferencesRepositoryTest {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var repository: DataStoreUserPreferencesRepository

    @get:Rule
    val tmpFolder: TemporaryFolder = TemporaryFolder.builder().assureDeletion().build()

    @Before
    fun setUp() {
        repository = DataStoreUserPreferencesRepository(
            tmpFolder.testUserPreferencesDataStore(testScope.backgroundScope)
        )
    }

    @Test
    fun `darkThemeConfig should be FOLLOW_SYSTEM by default`() = testScope.runTest {
        val expected = DarkThemeConfig.FOLLOW_SYSTEM

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun `darkThemeConfig should be FOLLOW_SYSTEM when set`() = testScope.runTest {
        val expected = DarkThemeConfig.FOLLOW_SYSTEM

        repository.setDarkThemeConfig(DarkThemeConfig.DARK)
        repository.setDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM)

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun `darkThemeConfig should be DARK when set`() = testScope.runTest {
        val expected = DarkThemeConfig.DARK

        repository.setDarkThemeConfig(DarkThemeConfig.DARK)

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun `darkThemeConfig should be LIGHT when set`() = testScope.runTest {
        val expected = DarkThemeConfig.LIGHT

        repository.setDarkThemeConfig(DarkThemeConfig.LIGHT)

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun `colorConfig should be DEFAULT by default`() = testScope.runTest {
        val expected = ColorConfig.DEFAULT

        val result = repository.userPreferences.first().colorConfig

        assertEquals(expected, result)
    }

    @Test
    fun `colorConfig should be DEFAULT when set`() = testScope.runTest {
        val expected = ColorConfig.DEFAULT

        repository.setColorConfig(ColorConfig.DEFAULT)

        val result = repository.userPreferences.first().colorConfig

        assertEquals(expected, result)
    }

    @Test
    fun `colorConfig should be DYNAMIC when set`() = testScope.runTest {
        val expected = ColorConfig.DYNAMIC

        repository.setColorConfig(ColorConfig.DYNAMIC)

        val result = repository.userPreferences.first().colorConfig

        assertEquals(expected, result)
    }

    @Test
    fun `lastUpdatedDateInMillis should be null by default`() = testScope.runTest {
        val result = repository.userPreferences.first().lastUpdatedDateInMillis

        assertNull(result)
    }

    @Test
    fun `lastUpdatedDateInMillis should return date when set`() = testScope.runTest {
        val expected = 1L

        repository.setLastUpdatedDateInMillis(expected)

        val result = repository.userPreferences.first().lastUpdatedDateInMillis

        assertEquals(expected, result)
    }
}
