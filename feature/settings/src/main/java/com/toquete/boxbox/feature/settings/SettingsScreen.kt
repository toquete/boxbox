package com.toquete.boxbox.feature.settings

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.preferences.model.UserPreferences
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
fun SettingsScreen(
    onDismiss: () -> Unit
) {
    val viewModel: SettingsViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    SettingsContent(state, onDismiss, viewModel::onSettingsItemClick)
}

@Composable
private fun SettingsContent(
    state: SettingsState,
    onDismiss: () -> Unit,
    onOptionSelected: (DarkThemeConfig) -> Unit
) {
    val configuration = LocalConfiguration.current
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.settings)) },
        text = {
            when (state) {
                SettingsState.Loading -> Text(stringResource(R.string.loading))
                is SettingsState.Success -> {
                    Column(
                        modifier = Modifier.selectableGroup(),
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(
                            text = stringResource(R.string.theme),
                            style = MaterialTheme.typography.titleMedium
                        )
                        SettingsRow(
                            text = stringResource(R.string.light),
                            isSelected = state.data.darkThemeConfig == DarkThemeConfig.LIGHT,
                            onClick = { onOptionSelected(DarkThemeConfig.LIGHT) }
                        )
                        SettingsRow(
                            text = stringResource(R.string.dark),
                            isSelected = state.data.darkThemeConfig == DarkThemeConfig.DARK,
                            onClick = { onOptionSelected(DarkThemeConfig.DARK) }
                        )
                        SettingsRow(
                            text = stringResource(R.string.follow_system),
                            isSelected = state.data.darkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM,
                            onClick = { onOptionSelected(DarkThemeConfig.FOLLOW_SYSTEM) }
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(android.R.string.ok))
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(text = stringResource(android.R.string.cancel))
            }
        }
    )
}

@Composable
private fun SettingsRow(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .selectable(
                selected = isSelected,
                role = Role.RadioButton,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = isSelected,
            onClick = null
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(text)
    }
}

@Preview(name = "Light", showBackground = false)
@Composable
fun SettingsContentLightPreview() {
    BoxBoxTheme {
        SettingsContent(
            state = SettingsState.Success(
                UserPreferences(darkThemeConfig = DarkThemeConfig.LIGHT)
            ),
            onDismiss = {},
            onOptionSelected = {}
        )
    }
}

@Preview(name = "Dark", showBackground = false, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SettingsContentDarkPreview() {
    BoxBoxTheme {
        Surface {
            SettingsContent(
                state = SettingsState.Success(
                    UserPreferences(darkThemeConfig = DarkThemeConfig.LIGHT)
                ),
                onDismiss = {},
                onOptionSelected = {}
            )
        }
    }
}
