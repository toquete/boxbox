package com.toquete.boxbox

import androidx.activity.compose.setContent
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.DialogNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoActivityResumedException
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.domain.repository.RaceRepository
import com.toquete.boxbox.feature.home.navigation.HOME_ROUTE
import com.toquete.boxbox.feature.raceresults.navigation.RACE_RESULT_ROUTE
import com.toquete.boxbox.feature.settings.navigation.SETTINGS_ROUTE
import com.toquete.boxbox.ui.MainActivity
import com.toquete.boxbox.ui.MainScreen
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import jakarta.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import com.toquete.boxbox.feature.home.R as homeR
import com.toquete.boxbox.feature.standings.R as standingsR

@HiltAndroidTest
class NavigationTest {

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val composeTestRule = createAndroidComposeRule<MainActivity>()

    @Inject
    lateinit var raceRepository: RaceRepository

    private lateinit var navController: TestNavHostController
    private val appName by composeTestRule.stringResource(R.string.app_name)
    private val standings by composeTestRule.stringResource(homeR.string.home_standings)
    private val constructors by composeTestRule.stringResource(standingsR.string.standings_constructors)
    private val drivers by composeTestRule.stringResource(standingsR.string.standings_drivers)
    private val races by composeTestRule.stringResource(homeR.string.home_races)

    @Before
    fun setupAppNavHost() {
        hiltRule.inject()
        composeTestRule.activity.setContent {
            navController = TestNavHostController(LocalContext.current).apply {
                navigatorProvider.addNavigator(ComposeNavigator())
                navigatorProvider.addNavigator(DialogNavigator())
            }
            BoxBoxTheme(
                darkTheme = false,
                dynamicColors = false
            ) {
                MainScreen(navController)
            }
        }
    }

    @Test
    fun verifyStartDestination() {
        composeTestRule.apply {
            onNodeWithText(appName).assertIsDisplayed()
            onNodeWithText(drivers).assertIsSelected()
            onNodeWithText(standings).assertIsSelected()
            onNodeWithTag("Home Settings Button").assertIsDisplayed()
        }
    }

    @Test
    fun verifyStateIsRestored() {
        composeTestRule.apply {
            onNodeWithText(constructors).performClick()
            onNodeWithText(races).performClick()
            onNodeWithText(standings).performClick()
            onNodeWithText(constructors).assertIsSelected()
        }
    }

    @Test
    fun verifyReselectTabStateIsRestored() {
        composeTestRule.apply {
            onNodeWithText(constructors).performClick()
            onNodeWithText(standings).performClick()
            onNodeWithText(constructors).assertIsSelected()
        }
    }

    @Test
    fun verifyRacesTabSelection() {
        composeTestRule.apply {
            onNodeWithText(races).performClick()

            onNodeWithTag("Home AppBar Title").assertTextEquals(races)
            onNode(hasText(races) and hasTestTag("Home Navigation Bar Item RACES")).assertIsSelected()
            onNodeWithTag("Home Settings Button").assertIsDisplayed()
        }
    }

    @Test
    fun verifySettingsDestination() {
        composeTestRule.apply {
            onNodeWithTag("Home Settings Button").performClick()

            assertEquals(SETTINGS_ROUTE, navController.currentBackStackEntry?.destination?.route)
        }
    }

    @Test(expected = NoActivityResumedException::class)
    fun verifyAppExitOnTopLevelDestination() {
        composeTestRule.apply {
            onNodeWithText(races).performClick()
            onNodeWithText(standings).performClick()
            Espresso.pressBack()
        }
    }

    @Test
    fun verifyBackFromAnyDestination() {
        composeTestRule.apply {
            onNodeWithText(races).performClick()
            Espresso.pressBack()
            onNodeWithText(standings).assertIsSelected()
        }
    }

    @Test
    fun verifyRaceResultDestination() {
        composeTestRule.apply {
            onNodeWithText(races).performClick()

            val races = runBlocking {
                raceRepository.getPastRacesBySeason(season = "2023", today = "2023-01-01T01:00:00Z").first()
            }
            val race = races.first()

            onNodeWithText(race.circuit.country).performClick()

            assertEquals(RACE_RESULT_ROUTE, navController.currentBackStackEntry?.destination?.route)
        }
    }

    @Test
    fun verifyRaceResultDestinationNavigateUp() {
        composeTestRule.apply {
            onNodeWithText(races).performClick()

            val races = runBlocking {
                raceRepository.getPastRacesBySeason(season = "2023", today = "2023-01-01T01:00:00Z").first()
            }
            val race = races.first()

            onNodeWithText(race.circuit.country).performClick()
            onNodeWithTag("Back Button").performClick()

            assertEquals(HOME_ROUTE, navController.currentBackStackEntry?.destination?.route)
        }
    }
}
