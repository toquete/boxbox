package com.toquete.boxbox

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.standings.drivers.presentation.DriversStandingViewModel
import com.toquete.boxbox.ui.theme.BoxBoxTheme
import com.toquete.boxbox.ui.theme.primaryLightDark

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoxBoxTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
                    Main()
                }
            }
        }
    }
}

@Composable
fun Main(viewModel: DriversStandingViewModel = viewModel()) {
    val state = viewModel.state
    Column {
        TopAppBar {
            Image(
                painter = painterResource(id = R.drawable.ic_f1),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.colors.primaryLightDark)
            )
        }
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(state.standings) { standing ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = "#${standing.position}",
                        style = MaterialTheme.typography.h4
                    )
                    Column(
                        modifier = Modifier
                            .weight(0.8f)
                            .padding(horizontal = 8.dp)
                    ) {
                        Text(
                            text = standing.driver,
                            style = MaterialTheme.typography.h4
                        )
                        Row {
                            Text(
                                modifier = Modifier.padding(end = 8.dp),
                                text = standing.nationality,
                                style = MaterialTheme.typography.subtitle1
                            )
                            Text(
                                text = standing.car,
                                style = MaterialTheme.typography.subtitle1
                            )
                        }
                    }
                    Text(
                        text = standing.points.toString(),
                        style = MaterialTheme.typography.h4
                    )
                }
                Divider()
            }
        }
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
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background) {
            Main()
        }
    }
}