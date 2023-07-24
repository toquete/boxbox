package com.toquete.boxbox.plugins

enum class BoxBoxBuildType(
    val applicationIdSuffix: String? = null,
    val versionNameSuffix: String? = null
) {
    RELEASE,
    MINIFIED(
        applicationIdSuffix = ".minified",
        versionNameSuffix = "-MINIFIED"
    ),
    DEBUG(
        applicationIdSuffix = ".debug",
        versionNameSuffix = "-DEBUG"
    )
}
