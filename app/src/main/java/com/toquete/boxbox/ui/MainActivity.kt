package com.toquete.boxbox.ui

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.navigation.BoxBoxNavHost
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
            val isDynamicColors = shouldUseDynamicColors(uiState)
            val navController = rememberNavController()

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

            BoxBoxTheme(
                darkTheme = isDarkTheme,
                dynamicColors = isDynamicColors
            ) {
                if (!uiState.isLoading) {
                    MainScreen(navController = navController)
                }
            }
        }
    }
}

@Composable
fun MainScreen(navController: NavHostController) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        BoxBoxNavHost(navController = navController)
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

private fun shouldUseDynamicColors(uiState: MainState): Boolean {
    return uiState.colorConfig == ColorConfig.DYNAMIC
}

private val DefaultLightScrim = Color.argb(0xe6, 0xFF, 0xFF, 0xFF)
private val DefaultDarkScrim = Color.argb(0x80, 0x1b, 0x1b, 0x1b)
