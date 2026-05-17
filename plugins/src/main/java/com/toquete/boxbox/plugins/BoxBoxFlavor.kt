package com.toquete.boxbox.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension
import com.android.build.api.dsl.LibraryProductFlavor
import java.util.Locale

enum class BoxBoxFlavor(
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null,
    val isDefault: Boolean = false
) {
    DEMO(
        applicationIdSuffix = ".demo",
        versionNameSuffix = "-DEMO"
    ),
    PROD(isDefault = true)
}

internal fun configureFlavors(commonExtension: CommonExtension) {
    commonExtension.apply {
        flavorDimensions += "version"
        BoxBoxFlavor.values().forEach { flavor ->
            productFlavors.create(flavor.name.lowercase(Locale.US)) {
                dimension = "version"
                if (this is LibraryProductFlavor) {
                    isDefault = flavor.isDefault
                }
                if (commonExtension is ApplicationExtension && this is ApplicationProductFlavor) {
                    applicationIdSuffix = flavor.applicationIdSuffix
                    versionNameSuffix = flavor.versionNameSuffix
                    isDefault = flavor.isDefault
                }
            }
        }
    }
}
