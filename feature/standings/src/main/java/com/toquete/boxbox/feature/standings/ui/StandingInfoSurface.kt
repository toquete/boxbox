package com.toquete.boxbox.feature.standings.ui

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
fun StandingInfoSurface(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = MaterialTheme.shapes.extraLarge,
        color = MaterialTheme.colorScheme.secondary,
        content = content
    )
}

@Preview(name = "Light")
@Composable
fun StandingInfoSurfaceLightPreview() {
    BoxBoxTheme {
        StandingInfoSurface {
            Text(
                modifier = Modifier.padding(6.dp),
                text = "Example"
            )
        }
    }
}

@Preview(name = "Dark", uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StandingInfoSurfaceDarkPreview() {
    BoxBoxTheme {
        StandingInfoSurface {
            Text(
                modifier = Modifier.padding(6.dp),
                text = "Example"
            )
        }
    }
}
