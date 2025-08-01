package com.toquete.boxbox.core.preferences.repository

import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.DataStoreTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

@OptIn(ExperimentalCoroutinesApi::class)
class DataStoreUserPreferencesRepositoryTest : DataStoreTest() {

    private val testScope = TestScope(UnconfinedTestDispatcher())

    private lateinit var repository: DataStoreUserPreferencesRepository

    @BeforeTest
    fun setUp() {
        repository = DataStoreUserPreferencesRepository(
            dataStore = getInMemoryDataStore(testScope.backgroundScope)
        )
    }

    @Test
    fun darkThemeConfig_FOLLOW_SYSTEM_default() = testScope.runTest {
        val expected = DarkThemeConfig.FOLLOW_SYSTEM

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun darkThemeConfig_FOLLOW_SYSTEM_set() = testScope.runTest {
        val expected = DarkThemeConfig.FOLLOW_SYSTEM

        repository.setDarkThemeConfig(DarkThemeConfig.DARK)
        repository.setDarkThemeConfig(DarkThemeConfig.FOLLOW_SYSTEM)

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun darkThemeConfig_DARK_set() = testScope.runTest {
        val expected = DarkThemeConfig.DARK

        repository.setDarkThemeConfig(DarkThemeConfig.DARK)

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun darkThemeConfig_LIGHT_set() = testScope.runTest {
        val expected = DarkThemeConfig.LIGHT

        repository.setDarkThemeConfig(DarkThemeConfig.LIGHT)

        val result = repository.userPreferences.first().darkThemeConfig

        assertEquals(expected, result)
    }

    @Test
    fun colorConfig_DEFAULT_default() = testScope.runTest {
        val expected = ColorConfig.DEFAULT

        val result = repository.userPreferences.first().colorConfig

        assertEquals(expected, result)
    }

    @Test
    fun colorConfig_DEFAULT_set() = testScope.runTest {
        val expected = ColorConfig.DEFAULT

        repository.setColorConfig(ColorConfig.DEFAULT)

        val result = repository.userPreferences.first().colorConfig

        assertEquals(expected, result)
    }

    @Test
    fun colorConfig_DYNAMIC_set() = testScope.runTest {
        val expected = ColorConfig.DYNAMIC

        repository.setColorConfig(ColorConfig.DYNAMIC)

        val result = repository.userPreferences.first().colorConfig

        assertEquals(expected, result)
    }

    @Test
    fun lastUpdatedDateInMillis_null_default() = testScope.runTest {
        val result = repository.userPreferences.first().lastUpdatedDateInMillis

        assertNull(result)
    }

    @Test
    fun lastUpdatedDateInMillis_date_set() = testScope.runTest {
        val expected = 1L

        repository.setLastUpdatedDateInMillis(expected)

        val result = repository.userPreferences.first().lastUpdatedDateInMillis

        assertEquals(expected, result)
    }
}
