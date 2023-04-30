package com.toquete.boxbox.feature.standings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.res.stringResource
import com.toquete.boxbox.core.model.FullConstructorStanding
import com.toquete.boxbox.core.model.FullDriverStanding
import com.toquete.boxbox.feature.standings.constructors.FullConstructorStandingsScreen
import com.toquete.boxbox.feature.standings.drivers.FullDriverStandingsScreen
import com.toquete.boxbox.feature.standings.model.StandingsTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun StandingsScreen(
    driverStandings: List<FullDriverStanding>,
    constructorStandings: List<FullConstructorStanding>
) {
    var selectedTab by remember { mutableStateOf(StandingsTab.DRIVERS) }
    val pagerState = rememberPagerState()
    val coroutineScope = rememberCoroutineScope()
    val pages = StandingsTab.values()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedTab = pages[page]
        }
    }

    Column {
        TabRow(selectedTabIndex = pages.indexOf(selectedTab)) {
            pages.forEachIndexed { index, standingsTab ->
                Tab(
                    selected = selectedTab == standingsTab,
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    text = { Text(stringResource(standingsTab.titleId)) }
                )
            }
        }
        HorizontalPager(
            pageCount = pages.size,
            state = pagerState
        ) { page ->
            when (pages[page]) {
                StandingsTab.DRIVERS -> FullDriverStandingsScreen(driverStandings)
                StandingsTab.CONSTRUCTORS -> FullConstructorStandingsScreen(constructorStandings)
            }
        }
    }
}