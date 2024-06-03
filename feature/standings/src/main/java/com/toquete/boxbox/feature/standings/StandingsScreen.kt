package com.toquete.boxbox.feature.standings

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.SingleChoiceSegmentedButtonRow
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.standings.constructors.ConstructorStandingsRoute
import com.toquete.boxbox.feature.standings.drivers.DriverStandingsRoute
import com.toquete.boxbox.feature.standings.model.StandingsTab
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
internal fun StandingsScreen() {
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
        SingleChoiceSegmentedButtonRow(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp)
        ) {
            pages.forEachIndexed { index, standingsTab ->
                SegmentedButton(
                    selected = index == pages.indexOf(selectedTab),
                    onClick = {
                        coroutineScope.launch {
                            pagerState.animateScrollToPage(index)
                        }
                    },
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = pages.size),
                    icon = { Icon(painter = painterResource(standingsTab.iconId), contentDescription = null) },
                    label = { Text(text = stringResource(standingsTab.titleId)) }
                )
            }
        }
        HorizontalPager(
            state = pagerState,
            userScrollEnabled = false
        ) { page ->
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
