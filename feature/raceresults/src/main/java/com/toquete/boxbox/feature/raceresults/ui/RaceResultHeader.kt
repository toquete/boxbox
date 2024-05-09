package com.toquete.boxbox.feature.raceresults.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme
import com.toquete.boxbox.core.ui.theme.FormulaOne

@Composable
internal fun RaceResultHeader() {
    Row(
        modifier = Modifier
            .wrapContentWidth(unbounded = true)
            .background(color = MaterialTheme.colorScheme.background)
            .padding(all = 8.dp)
    ) {
        Text(
            modifier = Modifier
                .width(50.dp)
                .padding(end = 4.dp),
            text = "Pos",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .width(200.dp)
                .padding(end = 4.dp),
            text = "Driver",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .width(150.dp)
                .padding(end = 4.dp),
            text = "Constructor",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .width(60.dp)
                .padding(end = 4.dp),
            text = "Laps",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .width(125.dp)
                .padding(end = 4.dp),
            text = "Time",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier
                .width(125.dp)
                .padding(end = 4.dp),
            text = "Status",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
        Text(
            modifier = Modifier.width(50.dp),
            text = "Pts",
            style = MaterialTheme.typography.bodyLarge.copy(fontFamily = FormulaOne),
            fontWeight = FontWeight.Bold
        )
    }
}

@UiModePreviews
@Composable
internal fun RaceResultHeaderPreview() {
    BoxBoxTheme {
        RaceResultHeader()
    }
}
