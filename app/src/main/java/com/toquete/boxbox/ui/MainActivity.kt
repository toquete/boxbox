package com.toquete.boxbox.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.toquete.boxbox.R
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.feature.settings.SettingsScreen
import com.toquete.boxbox.navigation.BoxBoxNavHost
import com.toquete.boxbox.util.monitor.NetworkMonitor
import com.toquete.boxbox.util.monitor.SyncMonitor
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var networkMonitor: NetworkMonitor

    @Inject
    lateinit var syncMonitor: SyncMonitor

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainState by mutableStateOf(MainState())
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.onEach {
                    uiState = it
                }.collect()
            }
        }

        splashScreen.setKeepOnScreenCondition { uiState.isLoading }

        enableEdgeToEdge()

        setContent {
            val isDarkTheme = shouldUseDarkTheme(uiState)

            DisposableEffect(isDarkTheme) {
                enableEdgeToEdge(
                    statusBarStyle = SystemBarStyle.auto(
                        Color.TRANSPARENT,
                        Color.TRANSPARENT
                    ) { isDarkTheme },
                    navigationBarStyle = SystemBarStyle.auto(
                        DefaultLightScrim,
                        DefaultDarkScrim
                    ) { isDarkTheme }
                )
                onDispose { }
            }

            BoxBoxTheme(darkTheme = isDarkTheme) {
                MainScreen(networkMonitor, syncMonitor)
            }
        }
    }
}

@Composable
fun MainScreen(
    networkMonitor: NetworkMonitor,
    syncMonitor: SyncMonitor
) {
    val mainAppState = rememberMainAppState(networkMonitor, syncMonitor)
    var showDialog by remember { mutableStateOf(false) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        if (showDialog) {
            SettingsScreen {
                showDialog = false
            }
        }
        MainScreenContent(
            mainAppState,
            onSettingsButtonClick = { showDialog = true }
        )
    }
}

@Composable
private fun MainScreenContent(
    mainAppState: MainAppState,
    onSettingsButtonClick: () -> Unit
) {
    val isOffline by mainAppState.isOffline.collectAsStateWithLifecycle()
    val hasFailed by mainAppState.hasFailed.collectAsStateWithLifecycle()
    val isSyncing by mainAppState.isSyncing.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }

    SnackbarMessage(isOffline, hasFailed, snackbarHostState)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        bottomBar = { BoxBoxNavigationBar(appState = mainAppState) },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                BoxBoxNavHost(
                    navController = mainAppState.navController,
                    isSyncing = isSyncing,
                    isOffline = isOffline,
                    onSettingsButtonClick = onSettingsButtonClick,
                )
            }
        }
    )
}

@Composable
private fun SnackbarMessage(
    isOffline: Boolean,
    hasFailed: Boolean,
    snackbarHostState: SnackbarHostState
) {
    val message = if (isOffline) stringResource(R.string.not_connected) else stringResource(R.string.fail_message)
    var isSnackbarDismissed by remember { mutableStateOf(true) }

    LaunchedEffect(isOffline, hasFailed) {
        if (isOffline || hasFailed || !isSnackbarDismissed) {
            val result = snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )
            isSnackbarDismissed = result == SnackbarResult.Dismissed
        }
    }
}

@Composable
private fun shouldUseDarkTheme(uiState: MainState): Boolean {
    return when (uiState.darkThemeConfig) {
        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
}

private val DefaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
