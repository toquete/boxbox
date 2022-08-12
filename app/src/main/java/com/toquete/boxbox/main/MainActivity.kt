package com.toquete.boxbox.main

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.toquete.boxbox.R
import com.toquete.boxbox.standings.driver.presentation.DriverStandingsScreen
import com.toquete.boxbox.ui.theme.BoxBoxTheme
import com.toquete.boxbox.ui.theme.primaryLightDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main() {
    Column {
        TopAppBar {
            Image(
                painter = painterResource(id = R.drawable.ic_f1),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryLightDark)
            )
        }
        DriverStandingsScreen()
    }
}

@Preview(name = "Main Light", showBackground = true)
@Composable
fun MainLightPreview() {
    BoxBoxTheme {
        Main()
    }
}

@Preview(name = "Main Dark", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun MainDarkPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colors.background) {
            Main()
        }
    }
}