package com.toquete.boxbox.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.home.navigation.HomeNavHost

@Composable
internal fun HomeRoute(
    viewModel: HomeViewModel = hiltViewModel(),
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        onSettingsButtonClick = onSettingsButtonClick,
        onRefresh = viewModel::refresh,
        builder = builder
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    state: HomeState,
    onSettingsButtonClick: () -> Unit = { },
    onRefresh: () -> Unit = { },
    builder: NavGraphBuilder.() -> Unit = { }
) {
    val homeViewState = rememberHomeViewState()
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bottomAppBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    Scaffold(
        modifier = Modifier
            .nestedScroll(bottomAppBarScrollBehavior.nestedScrollConnection)
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        topBar = {
            HomeTopAppBar(
                homeViewState = homeViewState,
                isOffline = state.isOffline,
                scrollBehavior = topAppBarScrollBehavior,
                onSettingsButtonClick = onSettingsButtonClick
            )
        },
        bottomBar = {
            HomeNavigationBar(
                homeViewState = homeViewState,
                scrollBehavior = bottomAppBarScrollBehavior
            )
        },
        snackbarHost = { SnackbarHost(hostState = homeViewState.snackbarHostState) }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = onRefresh,
            modifier = Modifier.padding(paddingValues)
        ) {
            Column {
                HomeNavHost(
                    modifier = Modifier.weight(1f),
                    homeViewState = homeViewState,
                    builder = builder
                )
                if (state.isAdBannerVisible) {
                    AdBanner()
                }
            }
            AnimatedVisibility(visible = state.isSyncing) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun HomeScreenPreview() {
    BoxBoxTheme {
        HomeScreen(state = HomeState())
    }
}
