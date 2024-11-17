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

internal fun configureFlavors(commonExtension: CommonExtension<*, *, *, *, *, *>) {
    commonExtension.apply {
        flavorDimensions += "version"
        productFlavors {
            BoxBoxFlavor.values().forEach {
                create(it.name.lowercase(Locale.US)) {
                    dimension = "version"
                    if (this is LibraryProductFlavor) {
                        isDefault = it.isDefault
                    }
                    if (commonExtension is ApplicationExtension && this is ApplicationProductFlavor) {
                        applicationIdSuffix = it.applicationIdSuffix
                        versionNameSuffix = it.versionNameSuffix
                        isDefault = it.isDefault
                    }
                }
            }
        }
    }
}
