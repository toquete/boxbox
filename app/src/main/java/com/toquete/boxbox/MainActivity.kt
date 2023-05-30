package com.toquete.boxbox

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne
import com.toquete.boxbox.feature.settings.SettingsScreen
import com.toquete.boxbox.feature.standings.StandingsScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        var uiState: MainState by mutableStateOf(MainState.Loading)
        lifecycleScope.launch {
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.onEach {
                    uiState = it
                }.collect()
            }
        }

        splashScreen.setKeepOnScreenCondition {
            when (uiState) {
                MainState.Loading -> true
                is MainState.Success -> false
            }
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            val systemUiController = rememberSystemUiController()
            val isDarkTheme = shouldUseDarkTheme(uiState)
            var showDialog by remember { mutableStateOf(false) }

            DisposableEffect(systemUiController, isDarkTheme) {
                systemUiController.setSystemBarsColor(
                    color = Color.Transparent,
                    darkIcons = !isDarkTheme
                )
                onDispose { }
            }

            BoxBoxTheme(darkTheme = isDarkTheme) {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    if (showDialog) {
                        SettingsScreen {
                            showDialog = false
                        }
                    }
                    MainScreen(
                        uiState,
                        onSettingsButtonClick = { showDialog = true }
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    state: MainState,
    onSettingsButtonClick: () -> Unit
) {
    when (state) {
        MainState.Loading -> Unit
        is MainState.Success -> MainScreenContent(
            state.isOnline,
            state.isSyncing,
            state.hasFailed,
            onSettingsButtonClick
        )
    }
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun MainScreenContent(
    isOnline: Boolean,
    isSyncing: Boolean,
    hasFailed: Boolean,
    onSettingsButtonClick: () -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val notConnectedMessage = stringResource(R.string.not_connected)
    val failedMessage = stringResource(R.string.fail_message)
    var isSnackbarDismissed by remember { mutableStateOf(false) }
    val mustShowSnackbar = !isOnline || !isSnackbarDismissed || hasFailed

    LaunchedEffect(isOnline) {
        if (mustShowSnackbar) {
            val message = if (!isOnline) notConnectedMessage else failedMessage
            val result = snackbarHostState.showSnackbar(
                message = message,
                duration = SnackbarDuration.Indefinite,
                withDismissAction = true
            )
            isSnackbarDismissed = result == SnackbarResult.Dismissed
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = stringResource(R.string.app_name),
                        style = MaterialTheme.typography.titleLarge.copy(
                            fontFamily = FormulaOne,
                            fontWeight = FontWeight.Bold
                        )
                    )
                },
                actions = {
                    if (!isOnline) {
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
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                StandingsScreen()
                AnimatedVisibility(visible = isSyncing) {
                    LinearProgressIndicator(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(4.dp)
                    )
                }
            }
        }
    )
}

@Composable
private fun shouldUseDarkTheme(
    uiState: MainState,
): Boolean = when (uiState) {
    MainState.Loading -> isSystemInDarkTheme()
    is MainState.Success -> when (uiState.darkThemeConfig) {
        DarkThemeConfig.FOLLOW_SYSTEM -> isSystemInDarkTheme()
        DarkThemeConfig.LIGHT -> false
        DarkThemeConfig.DARK -> true
    }
}

@Preview(name = "Main Light", showBackground = true)
@Composable
fun MainLightPreview() {
    BoxBoxTheme {
        MainScreenContent(
            isOnline = true,
            isSyncing = false,
            hasFailed = false,
            onSettingsButtonClick = {}
        )
    }
}

@Preview(name = "Main Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            MainScreenContent(
                isOnline = true,
                isSyncing = false,
                hasFailed = false,
                onSettingsButtonClick = {}
            )
        }
    }
}
