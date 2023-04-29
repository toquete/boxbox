package com.toquete.boxbox.feature.standings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowUpward
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
internal fun ScrollToUpButton(
    modifier: Modifier = Modifier,
    visible: Boolean,
    onClick: () -> Unit
) {
    AnimatedVisibility(
        modifier = modifier,
        visible = visible,
        enter = scaleIn(),
        exit = scaleOut()
    ) {
        FloatingActionButton(
            onClick = onClick,
            shape = FloatingActionButtonDefaults.largeShape
        ) {
            Icon(
                imageVector = Icons.Default.ArrowUpward,
                contentDescription = null
            )
        }
    }
}

@Preview
@Composable
fun ScrollToUpButtonLightPreview() {
    BoxBoxTheme {
        ScrollToUpButton(visible = true, onClick = {})
    }
}

@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun ScrollToUpButtonDarkPreview() {
    BoxBoxTheme {
        ScrollToUpButton(visible = true, onClick = {})
    }
}