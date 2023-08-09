package com.toquete.boxbox.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.WifiOff
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.ui.theme.FormulaOne

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoxBoxTopAppBar(
    appState: MainAppState,
    onSettingsButtonClick: () -> Unit
) {
    val isOffline by appState.isOffline.collectAsStateWithLifecycle()
    val destination = appState.currentTopLevelDestination
    if (destination != null) {
        CenterAlignedTopAppBar(
            title = {
                Text(
                    text = stringResource(destination.titleTextId),
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
    }
}
