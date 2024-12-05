package com.toquete.boxbox.feature.settings.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotSelected
import androidx.compose.ui.test.assertIsSelected
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class SettingsScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateSettingsScreenFollowSystemTheme() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    SettingsContent(
                        state = SettingsState(
                            isLoading = false,
                            darkThemeConfig = DarkThemeConfig.FOLLOW_SYSTEM,
                            colorConfig = ColorConfig.DEFAULT,
                            lastUpdatedTime = null
                        ),
                        onDismiss = {},
                        onThemeOptionSelected = {}
                    )
                }
            }

            onNodeWithText("Settings").assertIsDisplayed()
            onNodeWithText("Theme").assertIsDisplayed()
            onNodeWithText("Light")
                .assertIsDisplayed()
                .assertIsNotSelected()
            onNodeWithText("Dark")
                .assertIsDisplayed()
                .assertIsNotSelected()
            onNodeWithText("Follow system")
                .assertIsDisplayed()
                .assertIsSelected()
            onNodeWithTag("last_updated_time").assertIsNotDisplayed()
            onNodeWithText("Cancel").assertIsDisplayed()
            onNodeWithText("OK").assertIsDisplayed()
        }
    }

    @Test
    fun validateSettingsScreenLightTheme() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    SettingsContent(
                        state = SettingsState(
                            isLoading = false,
                            darkThemeConfig = DarkThemeConfig.LIGHT,
                            colorConfig = ColorConfig.DEFAULT,
                            lastUpdatedTime = "2021-09-01 12:00:00"
                        ),
                        onDismiss = {},
                        onThemeOptionSelected = {}
                    )
                }
            }

            onNodeWithText("Light").assertIsSelected()
            onNodeWithText("Dark").assertIsNotSelected()
            onNodeWithText("Follow system").assertIsNotSelected()
        }
    }

    @Test
    fun validateSettingsScreenDarkTheme() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    SettingsContent(
                        state = SettingsState(
                            isLoading = false,
                            darkThemeConfig = DarkThemeConfig.DARK,
                            colorConfig = ColorConfig.DEFAULT,
                            lastUpdatedTime = "2021-09-01 12:00:00"
                        ),
                        onDismiss = {},
                        onThemeOptionSelected = {}
                    )
                }
            }

            onNodeWithText("Light").assertIsNotSelected()
            onNodeWithText("Dark").assertIsSelected()
            onNodeWithText("Follow system").assertIsNotSelected()
        }
    }
}
