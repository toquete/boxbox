package com.toquete.boxbox.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.foundation.layout.windowInsetsBottomHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.BottomAppBarDefaults
import androidx.compose.material3.BottomAppBarScrollBehavior
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.settings.ui.SettingsScreen
import com.toquete.boxbox.navigation.BoxBoxNavHost

private const val SNACKBAR_OFFSET = -64

@Composable
internal fun HomeRoute(
    navController: NavHostController,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    HomeScreen(
        state = state,
        navController = navController,
        onIntent = viewModel::onIntent,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeScreen(
    state: HomeState,
    navController: NavHostController = rememberNavController(),
    onIntent: (HomeIntent) -> Unit = { }
) {
    val homeViewState = rememberHomeViewState(navController = navController)
    val topAppBarScrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    val bottomAppBarScrollBehavior = BottomAppBarDefaults.exitAlwaysScrollBehavior()
    val isBottomAppBarVisible by remember {
        derivedStateOf {
            bottomAppBarScrollBehavior.state.collapsedFraction != 1f
        }
    }
    var isAdLoaded by remember { mutableStateOf(false) }
    var isSettingsDialogVisible by remember { mutableStateOf(false) }

    if (isSettingsDialogVisible) {
        SettingsScreen {
            isSettingsDialogVisible = false
        }
    }

    Scaffold(
        modifier = Modifier
            .nestedScroll(bottomAppBarScrollBehavior.nestedScrollConnection)
            .nestedScroll(topAppBarScrollBehavior.nestedScrollConnection),
        contentWindowInsets = WindowInsets.safeDrawing,
        topBar = {
            if (homeViewState.isHomeDestination) {
                HomeTopAppBar(
                    homeViewState = homeViewState,
                    isOffline = state.isOffline,
                    scrollBehavior = topAppBarScrollBehavior,
                    onSettingsButtonClick = { isSettingsDialogVisible = true }
                )
            }
        },
        bottomBar = {
            HomeBottomBar(homeViewState, isBottomAppBarVisible, bottomAppBarScrollBehavior)
        },
        snackbarHost = {
            SnackbarHost(
                modifier = Modifier.offset {
                    val y = if (isAdLoaded) SNACKBAR_OFFSET.dp.roundToPx() else 0
                    IntOffset(0, y)
                },
                hostState = homeViewState.snackbarHostState
            )
        }
    ) { paddingValues ->
        PullToRefreshBox(
            isRefreshing = state.isRefreshing,
            onRefresh = { onIntent(HomeIntent.Refresh) },
            modifier = Modifier.padding(paddingValues)
        ) {
            Column {
                BoxBoxNavHost(navController = homeViewState.navController)
                if (state.isAdBannerVisible) {
                    AdBanner(onAdLoaded = { isAdLoaded = true })
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

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun HomeBottomBar(
    homeViewState: HomeViewState,
    isBottomAppBarVisible: Boolean,
    bottomAppBarScrollBehavior: BottomAppBarScrollBehavior
) {
    if (homeViewState.isHomeDestination) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentSize()
        ) {
            if (isBottomAppBarVisible) {
                HomeNavigationBar(
                    homeViewState = homeViewState,
                    scrollBehavior = bottomAppBarScrollBehavior
                )
            }
            Box(
                modifier = Modifier
                    .windowInsetsBottomHeight(WindowInsets.navigationBars)
                    .fillMaxWidth()
                    .align(Alignment.BottomCenter)
                    .background(Color.Transparent)
            )
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
