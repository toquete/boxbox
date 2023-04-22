package com.toquete.boxbox.feature.fullconstructorstandings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.model.FullConstructorStanding

@Composable
fun ConstructorStandingsScreen() {
    val viewModel: ConstructorStandingsViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    ConstructorStandingsContent(state)
}

@Composable
private fun ConstructorStandingsContent(state: ConstructorStandingsState) {
    Box(modifier = Modifier.fillMaxSize()) {
        when (state) {
            ConstructorStandingsState.Error -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "An error occurred"
                )
            }
            ConstructorStandingsState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            is ConstructorStandingsState.Success -> {
                ConstructorStandingsList(state.data)
            }
        }
    }
}

@Composable
private fun ConstructorStandingsList(list: List<FullConstructorStanding>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        items(list) { standing ->
            ConstructorStandingItem(standing)
            Divider()
        }
    }
}
