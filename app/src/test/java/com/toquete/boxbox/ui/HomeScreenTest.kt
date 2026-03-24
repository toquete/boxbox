package com.toquete.boxbox.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalMaterial3Api::class)
@RunWith(AndroidJUnit4::class)
class HomeScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateTopAppBarDefaultTitle() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    HomeTopAppBar(
                        homeViewState = rememberHomeViewState(),
                        isOffline = false,
                        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                    )
                }
            }

            onNodeWithTag("Home AppBar Title").assertTextEquals("BoxBox")
        }
    }

    @Test
    fun validateTopAppBarActions() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    HomeTopAppBar(
                        homeViewState = rememberHomeViewState(),
                        isOffline = false,
                        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
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
                    HomeTopAppBar(
                        homeViewState = rememberHomeViewState(),
                        isOffline = true,
                        scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
                    )
                }
            }

            onNodeWithTag("Home Settings Button").assertIsDisplayed()
            onNodeWithTag("Home Offline Icon").assertIsDisplayed()
        }
    }
}
