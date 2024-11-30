package com.toquete.boxbox.feature.standings.drivers

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.toquete.boxbox.core.model.Constructor
import com.toquete.boxbox.core.model.Driver
import com.toquete.boxbox.core.model.DriverStanding
import com.toquete.boxbox.core.ui.annotation.UiModePreviews
import com.toquete.boxbox.core.ui.theme.BoxBoxTheme

@Composable
internal fun DriverStandingsRoute(
    viewModel: DriverStandingsViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    DriverStandingsScreen(state)
}

@Composable
internal fun DriverStandingsScreen(state: DriverStandingsState) {
    val lazyListState = rememberLazyListState()
    val lifecycleOwner = LocalLifecycleOwner.current
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .testTag("Driver Standings List"),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            state = lazyListState
        ) {
            items(state.standings) { standing ->
                if (standing is DriverStanding) {
                    DriverStandingItem(standing)
                } else {
                    AndroidView(
                        modifier = Modifier.fillMaxWidth(),
                        factory = { context ->
                            AdView(context).apply {
                                setAdSize(AdSize.FULL_BANNER)
                                adUnitId = "ca-app-pub-3940256099942544/9214589741"
                                loadAd(AdRequest.Builder().build())
                            }
                        },
                        update = { adView ->
                            val lifecycleObserver = object : DefaultLifecycleObserver {
                                override fun onResume(owner: LifecycleOwner) {
                                    super.onResume(owner)
                                    adView.resume()
                                }

                                override fun onPause(owner: LifecycleOwner) {
                                    adView.pause()
                                    super.onPause(owner)
                                }

                                override fun onDestroy(owner: LifecycleOwner) {
                                    adView.destroy()
                                    lifecycleOwner.lifecycle.removeObserver(this)
                                    super.onDestroy(owner)
                                }
                            }

                            lifecycleOwner.lifecycle.addObserver(lifecycleObserver)
                        }
                    )
                }
            }
        }
    }
}

@UiModePreviews
@Composable
internal fun FullDriverStandingsScreenPreview() {
    BoxBoxTheme {
        Surface(color = MaterialTheme.colorScheme.background) {
            DriverStandingsScreen(
                state = DriverStandingsState(
                    standings = listOf(
                        DriverStanding(
                            position = 1,
                            points = "258",
                            wins = "7",
                            driver = Driver(
                                id = "max_verstappen",
                                firstName = "Max",
                                lastName = "Verstappen"
                            ),
                            constructor = Constructor(
                                id = "red_bull",
                                name = "Red Bull"
                            )
                        )
                    )
                )
            )
        }
    }
}
