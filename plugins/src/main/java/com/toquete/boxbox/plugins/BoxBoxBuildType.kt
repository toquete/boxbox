package com.toquete.boxbox.plugins

enum class BoxBoxBuildType(
    val applicationIdSuffix: String,
    val versionNameSuffix: String? = null
) {
    RELEASE(applicationIdSuffix = ".release"),
    MINIFIED(
        applicationIdSuffix = ".minified",
        versionNameSuffix = "-MINIFIED"
    ),
    DEBUG(
        applicationIdSuffix = ".debug",
        versionNameSuffix = "-DEBUG"
    )
}