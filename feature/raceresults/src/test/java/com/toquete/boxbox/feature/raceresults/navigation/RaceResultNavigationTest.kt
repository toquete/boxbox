package com.toquete.boxbox.feature.raceresults.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.navigation.BoxBoxRoute
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RaceResultNavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun navigateToRaceResult_displaysCorrectRoute() {
        composeTestRule.setContent {
            BoxBoxTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = BoxBoxRoute.Races
                ) {
                    composable<BoxBoxRoute.Races> {
                        Text(text = "Races Screen")
                    }
                    composable<BoxBoxRoute.RaceResult> { backStackEntry ->
                        val route = backStackEntry.toRoute<BoxBoxRoute.RaceResult>()
                        Text(text = "Round: ${route.round}")
                        Text(text = "Race: ${route.race}")
                    }
                }
                LaunchedEffect(Unit) {
                    navController.navigateToRaceResult(round = 5, race = "Monaco Grand Prix")
                }
            }
        }

        composeTestRule.onNodeWithText("Round: 5").assertIsDisplayed()
        composeTestRule.onNodeWithText("Race: Monaco Grand Prix").assertIsDisplayed()
    }

    @Test
    fun navigateToRaceResult_withDifferentParameters() {
        composeTestRule.setContent {
            BoxBoxTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = BoxBoxRoute.Races
                ) {
                    composable<BoxBoxRoute.Races> {
                        Text(text = "Races Screen")
                    }
                    composable<BoxBoxRoute.RaceResult> { backStackEntry ->
                        val route = backStackEntry.toRoute<BoxBoxRoute.RaceResult>()
                        Text(text = "Round: ${route.round}")
                        Text(text = "Race: ${route.race}")
                    }
                }
                LaunchedEffect(Unit) {
                    navController.navigateToRaceResult(round = 1, race = "Bahrain")
                }
            }
        }

        composeTestRule.onNodeWithText("Round: 1").assertIsDisplayed()
        composeTestRule.onNodeWithText("Race: Bahrain").assertIsDisplayed()
    }

    @Test
    fun clickNavigatesToRaceResult() {
        composeTestRule.setContent {
            BoxBoxTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = BoxBoxRoute.Races
                ) {
                    composable<BoxBoxRoute.Races> {
                        Button(
                            onClick = { navController.navigateToRaceResult(round = 10, race = "British Grand Prix") }
                        ) {
                            Text(text = "Go to Race Result")
                        }
                    }
                    composable<BoxBoxRoute.RaceResult> { backStackEntry ->
                        val route = backStackEntry.toRoute<BoxBoxRoute.RaceResult>()
                        Text(text = "Round: ${route.round}")
                        Text(text = "Race: ${route.race}")
                    }
                }
            }
        }

        composeTestRule.onNodeWithText("Go to Race Result").performClick()
        composeTestRule.onNodeWithText("Round: 10").assertIsDisplayed()
        composeTestRule.onNodeWithText("Race: British Grand Prix").assertIsDisplayed()
    }
}
