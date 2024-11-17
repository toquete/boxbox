package com.toquete.boxbox.feature.races.ui

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class RaceItemTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateRaceItem() {
        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    RaceItem(races.first())
                }
            }

            onNodeWithTag("Circuit Image", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Flag", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Country", useUnmergedTree = true).assertTextEquals("Bahrain")
            onNodeWithTag("Chevron", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Divider", useUnmergedTree = true).assertIsDisplayed()
            onNodeWithTag("Circuit Name", useUnmergedTree = true)
                .assertTextEquals("Bahrain International Circuit")
            onNodeWithTag("Month", useUnmergedTree = true).assertTextEquals("MAR")
            onNodeWithTag("Date", useUnmergedTree = true).assertTextEquals("05")
        }
    }
}
