package com.toquete.boxbox.core.ui.custom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import coil.compose.AsyncImagePainter
import coil.compose.rememberAsyncImagePainter
import coil.decode.Decoder
import coil.request.CachePolicy
import coil.request.ImageRequest
import coil.size.Size

@Composable
fun BoxBoxAsyncImage(
    modifier: Modifier = Modifier,
    data: String?,
    @DrawableRes error: Int? = null,
    @DrawableRes placeholder: Int? = null,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    decoder: Decoder.Factory? = null
) {
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(LocalContext.current)
            .data(data)
            .size(Size.ORIGINAL)
            .diskCacheKey(data)
            .diskCachePolicy(CachePolicy.ENABLED)
            .apply {
                decoder?.let { decoderFactory(decoder) }
            }
            .build()
    )

    val fallback = when (painter.state) {
        is AsyncImagePainter.State.Loading -> placeholder
        is AsyncImagePainter.State.Error -> error
        else -> null
    }

    if (fallback == null) {
        Image(
            modifier = modifier,
            painter = painter,
            contentDescription = contentDescription
        )
    } else {
        Image(
            modifier = modifier,
            painter = painterResource(fallback),
            contentDescription = contentDescription,
            colorFilter = colorFilter
        )
    }
}
