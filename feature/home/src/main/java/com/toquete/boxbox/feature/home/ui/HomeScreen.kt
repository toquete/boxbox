package com.toquete.boxbox.feature.home.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import com.toquete.boxbox.feature.home.navigation.HomeNavHost

@Composable
fun HomeScreen(
    isOffline: Boolean,
    isSyncing: Boolean,
    onSettingsButtonClick: () -> Unit,
    builder: NavGraphBuilder.() -> Unit
) {
    val homeState = rememberHomeState()
    Scaffold(
        topBar = {
            HomeTopAppBar(
                homeState = homeState,
                isOffline = isOffline,
                onSettingsButtonClick = onSettingsButtonClick
            )
        },
        bottomBar = {
            HomeNavigationBar(homeState = homeState)
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            HomeNavHost(
                homeState = homeState,
                builder = builder
            )
            AnimatedVisibility(visible = isSyncing) {
                LinearProgressIndicator(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(4.dp)
                )
            }
        }
    }
}
