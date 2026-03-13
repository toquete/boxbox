package com.toquete.boxbox.core.ui.custom

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImagePainter
import coil3.compose.rememberAsyncImagePainter
import coil3.imageLoader
import coil3.request.ImageRequest
import coil3.size.Size
import coil3.svg.SvgDecoder

@Composable
fun BoxBoxAsyncImage(
    modifier: Modifier = Modifier,
    data: String?,
    @DrawableRes error: Int? = null,
    @DrawableRes placeholder: Int? = null,
    contentDescription: String? = null,
    colorFilter: ColorFilter? = null,
    onState: (AsyncImagePainter.State) -> Unit = {},
) {
    val context = LocalContext.current
    val isSvg = data?.endsWith(".svg", ignoreCase = true) == true
    val request = ImageRequest.Builder(context)
        .data(data)
        .size(Size.ORIGINAL)
        .apply {
            if (isSvg) decoderFactory(SvgDecoder.Factory())
        }
        .build()
    val painter = rememberAsyncImagePainter(
        model = request,
        onState = onState
    )
    context.imageLoader.enqueue(request)

    val painterState by painter.state.collectAsStateWithLifecycle()
    val fallback = when (painterState) {
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
