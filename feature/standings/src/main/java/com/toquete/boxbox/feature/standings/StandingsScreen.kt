package com.toquete.boxbox.feature.standings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.standings.constructors.ConstructorStandingsRoute
import com.toquete.boxbox.feature.standings.drivers.DriverStandingsRoute
import com.toquete.boxbox.feature.standings.model.StandingsTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun StandingsScreen(
    isOffline: Boolean = false,
    isSyncing: Boolean = false,
    onSettingsButtonClick: () -> Unit = { }
) {
    var selectedTab by remember { mutableStateOf(StandingsTab.DRIVERS) }
    val pagerState = rememberPagerState(
        pageCount = { StandingsTab.entries.size }
    )
    val coroutineScope = rememberCoroutineScope()
    val pages = StandingsTab.entries.toTypedArray()

    LaunchedEffect(pagerState) {
        snapshotFlow { pagerState.currentPage }.collect { page ->
            selectedTab = pages[page]
        }
    }

    Column {
        CenterAlignedTopAppBar(
            windowInsets = WindowInsets(top = 0.dp),
            title = {
                Text(
                    text = stringResource(R.string.boxbox),
                    style = MaterialTheme.typography.titleLarge.copy(
                        fontFamily = FormulaOne,
                        fontWeight = FontWeight.Bold
                    )
                )
            },
            actions = {
                if (isOffline) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.WifiOff,
                        contentDescription = null
                    )
                }
                IconButton(onClick = onSettingsButtonClick) {
                    Icon(
                        modifier = Modifier.size(30.dp),
                        imageVector = Icons.Default.Settings,
                        contentDescription = null
                    )
                }
            }
        )
        AnimatedVisibility(visible = isSyncing) {
            LinearProgressIndicator(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
            )
        }
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
        HorizontalPager(state = pagerState) { page ->
            when (pages[page]) {
                StandingsTab.DRIVERS -> DriverStandingsRoute()
                StandingsTab.CONSTRUCTORS -> ConstructorStandingsRoute()
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun StandingsScreenPreview() {
    BoxBoxTheme {
        StandingsScreen()
    }
}
