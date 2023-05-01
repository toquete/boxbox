package com.toquete.boxbox.core.ui.custom

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil.ImageLoader
import coil.compose.AsyncImage
import coil.decode.Decoder
import coil.request.CachePolicy
import coil.request.ImageRequest

@Composable
fun BoxBoxAsyncImage(
    modifier: Modifier = Modifier,
    data: String?,
    contentDescription: String?,
    decoder: Decoder.Factory? = null
) {
    val imageRequest = ImageRequest.Builder(LocalContext.current)
        .data(data)
        .diskCacheKey(data)
        .diskCachePolicy(CachePolicy.ENABLED)
        .apply {
            decoder?.let { decoderFactory(decoder) }
        }
        .build()

    AsyncImage(
        modifier = modifier,
        model = imageRequest,
        contentDescription = contentDescription,
        imageLoader = ImageLoader.Builder(LocalContext.current)
            .respectCacheHeaders(enable = false)
            .build()
    )
}