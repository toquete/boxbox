package com.toquete.boxbox.feature.races

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import com.toquete.boxbox.core.testing.data.races
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.races.ui.RaceItem
import org.junit.Rule
import org.junit.Test

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

            onNodeWithTag("Circuit Image").assertIsDisplayed()
            onNodeWithTag("Flag").assertIsDisplayed()
            onNodeWithTag("Country").assertTextEquals("Bahrain")
            onNodeWithTag("Divider").assertIsDisplayed()
            onNodeWithTag("Circuit Name").assertTextEquals("Bahrain International Circuit")
            onNodeWithTag("Month").assertTextEquals("MAR")
            onNodeWithTag("Date").assertTextEquals("05")
        }
    }
}
