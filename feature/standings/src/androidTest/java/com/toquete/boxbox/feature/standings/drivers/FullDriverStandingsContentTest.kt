package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.filterToOne
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performScrollToIndex
import com.toquete.boxbox.core.testing.data.fullDriverStandings
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import org.junit.Rule
import org.junit.Test

class FullDriverStandingsContentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun validateScrollButtonIsNotVisibleWhenScreenStarts() {
        val list = fullDriverStandings.toMutableList()
        (1..10).forEach { _ -> list.add(list.first().copy()) }

        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    FullDriverStandingsContent(FullDriversStandingsState.Success(list))
                }
            }

            onNodeWithTag("Scroll Button").assertDoesNotExist()
        }
    }

    @Test
    fun validateScrollButtonIsVisibleWhenScreenIsScrolled() {
        val list = fullDriverStandings.toMutableList()
        (1..10).forEach { _ -> list.add(list.first().copy()) }

        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    FullDriverStandingsContent(FullDriversStandingsState.Success(list))
                }
            }

            onNodeWithTag("Driver Standings List").performScrollToIndex(1)
            onNodeWithTag("Scroll Button").assertIsDisplayed()
        }
    }

    @Test
    fun validateScrollButtonClick() {
        val list = fullDriverStandings.toMutableList()
        (2..10).forEach { list.add(list.first().copy(position = it)) }

        with(composeTestRule) {
            setContent {
                BoxBoxTheme {
                    FullDriverStandingsContent(FullDriversStandingsState.Success(list))
                }
            }

            onNodeWithTag("Driver Standings List").performScrollToIndex(9)
            onNodeWithTag("Scroll Button").performClick()
            onNodeWithTag("Driver Standings List")
                .onChildren()
                .onFirst()
                .onChildren()
                .filterToOne(hasText("1"))
                .assertIsDisplayed()
        }
    }
}
