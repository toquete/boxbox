package com.toquete.boxbox.core.ui.custom

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

private const val ANIMATION_DURATION = 500
private const val ANIMATION_LABEL = "FlipCardRotation"
private const val FRONT_MAX_ANGLE = 90f
private const val HALF_ROTATION_ANGLE = 180f

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FlipCard(
    side: CardSide,
    onClick: (CardSide) -> Unit,
    duration: Int = ANIMATION_DURATION,
    modifier: Modifier = Modifier,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {
    val rotation = animateFloatAsState(
        targetValue = side.angle,
        animationSpec = tween(
            durationMillis = duration,
            easing = FastOutSlowInEasing,
        ),
        label = ANIMATION_LABEL
    )
    Card(
        modifier = modifier.graphicsLayer { rotationY = rotation.value },
        onClick = { onClick(side) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            if (rotation.value <= FRONT_MAX_ANGLE) {
                front()
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer { rotationY = HALF_ROTATION_ANGLE },
                ) {
                    back()
                }
            }
        }
    }
}

enum class CardSide(val angle: Float) {
    FRONT(angle = 0f) {
        override fun flip(): CardSide = BACK
    },
    BACK(angle = 180f) {
        override fun flip(): CardSide = FRONT
    };

    abstract fun flip(): CardSide
}
