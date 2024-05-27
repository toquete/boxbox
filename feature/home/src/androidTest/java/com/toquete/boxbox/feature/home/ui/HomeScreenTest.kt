package com.toquete.boxbox.feature.home.ui

import androidx.compose.material3.Text
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateTopAppBarTitleOnMainScreen() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    HomeScreen(
                        state = HomeState(),
                        builder = {
                            testNavGraph()
                        }
                    )
                }
            }

            onNodeWithTag("Home AppBar Title").assertTextEquals("BoxBox")
        }
    }

    @Test
    fun validateTopAppBarTitleOnRacesScreen() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    HomeScreen(
                        state = HomeState(),
                        builder = {
                            testNavGraph()
                        }
                    )
                }
            }

            onNodeWithTag("Home Navigation Bar Item RACES").performClick()
            onNodeWithTag("Home AppBar Title").assertTextEquals("Races")
        }
    }

    @Test
    fun validateTopAppBarActions() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    HomeScreen(
                        state = HomeState(),
                        builder = {
                            testNavGraph()
                        }
                    )
                }
            }

            onNodeWithTag("Home Settings Button").assertIsDisplayed()
            onNodeWithTag("Home Offline Icon").assertIsNotDisplayed()
        }
    }

    @Test
    fun validateOfflineIcon() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    HomeScreen(
                        state = HomeState(isOffline = true),
                        builder = {
                            testNavGraph()
                        }
                    )
                }
            }

            onNodeWithTag("Home Settings Button").assertIsDisplayed()
            onNodeWithTag("Home Offline Icon").assertIsDisplayed()
        }
    }

    private fun NavGraphBuilder.testNavGraph() {
        composable(route = "standings") {
            Text("Standings")
        }
        composable(route = "races") {
            Text("Races")
        }
    }
}
