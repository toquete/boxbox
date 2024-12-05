package com.toquete.boxbox.feature.settings.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.toquete.boxbox.core.model.ColorConfig
import com.toquete.boxbox.core.model.DarkThemeConfig
import com.toquete.boxbox.core.model.UserPreferences
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.supportsDynamicTheming
import com.toquete.boxbox.feature.settings.R

@Composable
internal fun SettingsScreen(
    viewModel: SettingsViewModel = hiltViewModel(),
    onDismiss: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    SettingsContent(
        state = state,
        onDismiss = onDismiss,
        onThemeOptionSelected = viewModel::onThemeSettingsItemClick,
        onColorOptionSelected = viewModel::onColorSettingsItemClick
    )
}

@Composable
internal fun SettingsContent(
    state: SettingsState,
    onDismiss: () -> Unit = { },
    onThemeOptionSelected: (DarkThemeConfig) -> Unit = { },
    onColorOptionSelected: (ColorConfig) -> Unit = { }
) {
    val configuration = LocalConfiguration.current
    AlertDialog(
        properties = DialogProperties(usePlatformDefaultWidth = false),
        modifier = Modifier.widthIn(max = configuration.screenWidthDp.dp - 80.dp),
        onDismissRequest = onDismiss,
        title = { Text(stringResource(R.string.settings)) },
        text = {
            when (state) {
                SettingsState.Loading -> Text(stringResource(R.string.settings_loading))
                is SettingsState.Success -> {
                    Column {
                        Column(
                            modifier = Modifier.selectableGroup(),
                            verticalArrangement = Arrangement.spacedBy(16.dp)
                        ) {
                            Text(
                                text = stringResource(R.string.settings_theme),
                                style = MaterialTheme.typography.titleMedium
                            )
                            SettingsRow(
                                text = stringResource(R.string.settings_light),
                                isSelected = state.data.darkThemeConfig == DarkThemeConfig.LIGHT,
                                onClick = { onThemeOptionSelected(DarkThemeConfig.LIGHT) }
                            )
                            SettingsRow(
                                text = stringResource(R.string.settings_dark),
                                isSelected = state.data.darkThemeConfig == DarkThemeConfig.DARK,
                                onClick = { onThemeOptionSelected(DarkThemeConfig.DARK) }
                            )
                            SettingsRow(
                                text = stringResource(R.string.settings_follow_system),
                                isSelected = state.data.darkThemeConfig == DarkThemeConfig.FOLLOW_SYSTEM,
                                onClick = { onThemeOptionSelected(DarkThemeConfig.FOLLOW_SYSTEM) }
                            )
                        }
                        if (supportsDynamicTheming()) {
                            Column(
                                modifier = Modifier
                                    .padding(top = 16.dp)
                                    .selectableGroup(),
                                verticalArrangement = Arrangement.spacedBy(16.dp)
                            ) {
                                Text(
                                    text = stringResource(R.string.settings_color),
                                    style = MaterialTheme.typography.titleMedium
                                )
                                SettingsRow(
                                    text = stringResource(R.string.settings_default),
                                    isSelected = state.data.colorConfig == ColorConfig.DEFAULT,
                                    onClick = { onColorOptionSelected(ColorConfig.DEFAULT) }
                                )
                                SettingsRow(
                                    text = stringResource(R.string.settings_dynamic),
                                    isSelected = state.data.colorConfig == ColorConfig.DYNAMIC,
                                    onClick = { onColorOptionSelected(ColorConfig.DYNAMIC) }
                                )
                            }
                        }
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

@UiModePreviews
@Composable
internal fun SettingsContentPreview() {
    BoxBoxTheme {
        SettingsContent(
            state = SettingsState.Success(
                UserPreferences(
                    darkThemeConfig = DarkThemeConfig.LIGHT,
                    colorConfig = ColorConfig.DEFAULT,
                    lastUpdatedDateInMillis = null
                )
            )
        )
    }
}
