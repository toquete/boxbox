package com.toquete.boxbox.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
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
        builder = builder
    )
}

@Composable
internal fun HomeScreen(
    state: HomeState,
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    val homeViewState = rememberHomeViewState()
    Scaffold(
        topBar = {
            HomeTopAppBar(
                homeViewState = homeViewState,
                isOffline = state.isOffline,
                onSettingsButtonClick = onSettingsButtonClick
            )
        },
        bottomBar = {
            HomeNavigationBar(homeViewState = homeViewState)
        },
        snackbarHost = { SnackbarHost(hostState = homeViewState.snackbarHostState) }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeNavHost(
                homeViewState = homeViewState,
                builder = builder
            )
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
