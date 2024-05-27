package com.toquete.boxbox.feature.home.ui

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
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.theme.FormulaOne

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun HomeTopAppBar(
    homeViewState: HomeViewState,
    isOffline: Boolean,
    onSettingsButtonClick: () -> Unit = { }
) {
    val title = homeViewState.currentHomeDestination?.titleTextId
    CenterAlignedTopAppBar(
        title = {
            Text(
                modifier = Modifier.testTag("Home AppBar Title"),
                text = title?.let { stringResource(title) }.orEmpty(),
                style = MaterialTheme.typography.titleLarge.copy(
                    fontFamily = FormulaOne,
                    fontWeight = FontWeight.Bold
                )
            )
        },
        actions = {
            if (isOffline) {
                Icon(
                    modifier = Modifier
                        .size(30.dp)
                        .testTag("Home Offline Icon"),
                    imageVector = Icons.Default.WifiOff,
                    contentDescription = null
                )
            }
            IconButton(
                modifier = Modifier.testTag("Home Settings Button"),
                onClick = onSettingsButtonClick
            ) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.Settings,
                    contentDescription = null
                )
            }
        }
    )
}
