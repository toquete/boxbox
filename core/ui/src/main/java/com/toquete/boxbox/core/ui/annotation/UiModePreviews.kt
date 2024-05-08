package com.toquete.boxbox.core.ui.annotation

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Preview(
    name = "Light Mode",
    group = "UI Mode",
    showBackground = true
)
@Preview(
    name = "Dark Mode",
    group = "UI Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
annotation class UiModePreviews
