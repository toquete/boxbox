package com.toquete.boxbox.plugins

import com.android.build.api.dsl.ApplicationExtension
import com.android.build.api.dsl.ApplicationProductFlavor
import com.android.build.api.dsl.CommonExtension

enum class BoxBoxFlavor(
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null
) {
    DEMO(
        applicationIdSuffix = ".demo",
        versionNameSuffix = "-DEMO"
    ),
    PROD
}

fun configureFlavors(commonExtension: CommonExtension<*, *, *, *>) {
    commonExtension.apply {
        flavorDimensions += "version"
        productFlavors {
            BoxBoxFlavor.values().forEach {
                create(it.name.toLowerCase()) {
                    dimension = "version"
                    if (this@apply is ApplicationExtension && this is ApplicationProductFlavor) {
                        if (it.applicationIdSuffix != null) {
                            applicationIdSuffix = it.applicationIdSuffix
                        }
                        if (it.versionNameSuffix != null) {
                            versionNameSuffix = it.versionNameSuffix
                        }
                    }
                }
            }
        }
    }
}
