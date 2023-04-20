package com.toquete.boxbox.feature.constructorstandings

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.model.Constructor
import com.toquete.boxbox.model.ConstructorStanding

@Composable
internal fun ConstructorStandingItem(standing: ConstructorStanding) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = standing.position,
            style = MaterialTheme.typography.titleLarge
        )
        Text(
            modifier = Modifier
                .weight(0.8f)
                .padding(horizontal = 8.dp),
            text = standing.constructor.name,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
        )
        Text(
            modifier = Modifier.align(Alignment.CenterVertically),
            text = standing.points,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Medium
        )
    }
}

@Preview(name = "Item Light", showBackground = true)
@Composable
private fun ConstructorStandingItemLightPreview() {
    BoxBoxTheme {
        ConstructorStandingItem(
            standing = ConstructorStanding(
                position = "1",
                points = "258",
                constructor = Constructor(
                    id = "red_bull",
                    name = "Red Bull",
                    imageUrl = null
                )
            )
        )
    }
}

@Preview(name = "Item Dark", showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
private fun ConstructorStandingItemDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            ConstructorStandingItem(
                standing = ConstructorStanding(
                    position = "1",
                    points = "258",
                    constructor = Constructor(
                        id = "red_bull",
                        name = "Red Bull",
                        imageUrl = null
                    )
                )
            )
        }
    }
}