package com.toquete.boxbox.feature.fullconstructorstandings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.toquete.boxbox.model.FullConstructorStanding

@Composable
fun FullConstructorStandingsScreen(isSyncing: Boolean) {
    val viewModel: FullConstructorStandingsViewModel = viewModel()
    val state by viewModel.state.collectAsStateWithLifecycle()
    ConstructorStandingsContent(state, isSyncing)
}

@Composable
private fun ConstructorStandingsContent(
    state: FullConstructorStandingsState,
    isSyncing: Boolean
) {
    Box(modifier = Modifier.fillMaxSize()) {
        when {
            state is FullConstructorStandingsState.Error -> {
                Text(
                    modifier = Modifier.align(Alignment.Center),
                    text = "An error occurred"
                )
            }
            isSyncing || state is FullConstructorStandingsState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
            state is FullConstructorStandingsState.Success -> {
                ConstructorStandingsList(state.data)
            }
        }
    }
}

@Composable
private fun ConstructorStandingsList(list: List<FullConstructorStanding>) {
    LazyColumn(modifier = Modifier.fillMaxSize()) {
        itemsIndexed(list) { index, standing ->
            FullConstructorStandingItem(standing)

            if (index == list.lastIndex) {
                Divider(
                    thickness = 16.dp,
                    color = Color.Transparent
                )
            }
        }
    }
}
