package com.toquete.boxbox.feature.home.ui

import android.content.Context
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.compose.LifecycleResumeEffect
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdSize
import com.google.android.gms.ads.AdView
import com.toquete.boxbox.feature.home.R

@Composable
fun AdBanner(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val isPreviewMode = LocalInspectionMode.current
    val deviceWidth = LocalConfiguration.current.screenWidthDp
    var adView by remember { mutableStateOf<AdView?>(null) }
    var parent by remember { mutableStateOf<FrameLayout?>(null) }

    LaunchedEffect(context) {
        adView?.destroy()
        adView = getAdView(context, deviceWidth, isPreviewMode)
    }

    if (isPreviewMode) {
        Text(
            modifier = modifier,
            text = "Google Mobile Ads preview banner."
        )
        return
    }

    AndroidView(
        modifier = modifier.wrapContentSize(),
        factory = { FrameLayout(it).also { parent = it } },
        update = { layout ->
            disposeLayout(adView, layout)
            adView?.let { layout.addView(it) }
        },
    )

    // Pause and resume the AdView when the lifecycle is paused and resumed.
    LifecycleResumeEffect(adView) {
        adView?.resume()
        onPauseOrDispose { adView?.pause() }
    }

    // Clean up the AdView after use.
    DisposableEffect(Unit) {
        onDispose { disposeLayout(adView, parent) }
    }
}

private fun getAdView(context: Context, width: Int, isPreviewMode: Boolean): AdView? {
    if (isPreviewMode) {
        return null
    }

    return AdView(context).apply {
        adUnitId = resources.getString(R.string.home_ad_unit_id)
        setAdSize(AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(context, width))
        loadAd(AdRequest.Builder().build())
    }
}

private fun disposeLayout(adView: AdView?, layout: FrameLayout?) {
    // Ensure AdViews and Composable references are up to date.
    (adView?.parent as? ViewGroup)?.removeView(adView)
    layout?.removeAllViews()
}
